package com.folium.core.sync.setup

import com.folium.core.resources.failUnless
import com.folium.core.resources.uiText
import com.folium.core.resources.LocalizedException
import com.folium.core.resources.UiText
import com.folium.core.resources.R
import android.content.Context
import android.net.Uri
import com.folium.core.common.DispatcherProvider
import com.folium.core.common.runCatchingCancellable
import com.folium.core.datastore.settings.SettingsRegistry
import com.folium.core.datastore.settings.SettingsRepository
import com.folium.core.sync.asset.CloudAssetCipher
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withContext
import javax.crypto.AEADBadTagException
import org.json.JSONObject

sealed interface GitHubSyncSettingsTransferOutcome {
    data object Success : GitHubSyncSettingsTransferOutcome
    data object MissingPassphrase : GitHubSyncSettingsTransferOutcome
    data class Failed(val message: UiText) : GitHubSyncSettingsTransferOutcome
}

@Singleton
class GitHubSyncSettingsTransfer @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val settingsRepository: SettingsRepository,
    private val dispatchers: DispatcherProvider,
) {
    private val cipher = CloudAssetCipher()

    suspend fun exportTo(destination: Uri): GitHubSyncSettingsTransferOutcome = withContext(dispatchers.io) {
        val settings = settingsRepository.exportToMap(includeNonExportable = true)
        val passphrase = settings[SettingsRegistry.GithubSyncPassphrase.key].orEmpty().toCharArray()
        if (passphrase.isEmpty()) return@withContext GitHubSyncSettingsTransferOutcome.MissingPassphrase
        try {
            val plaintext = buildPlaintext(settings).toByteArray(Charsets.UTF_8)
            val encrypted = cipher.encrypt(plaintext, passphrase, TransferAad)
            failUnless(
                encrypted.size <= MaxEncryptedTransferBytes,
                R.string.settings_error_github_sync_settings_export_too_large,
            )
            val output = context.contentResolver.openOutputStream(destination)
                ?: return@withContext GitHubSyncSettingsTransferOutcome.Failed(UiText.Res(R.string.settings_error_could_not_open_selected_location))
            output.use { it.write(encrypted) }
            GitHubSyncSettingsTransferOutcome.Success
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (throwable: Throwable) {
            runCatchingCancellable { context.contentResolver.delete(destination, null, null) }
            GitHubSyncSettingsTransferOutcome.Failed(throwable.uiText(R.string.settings_error_github_sync_settings_export_failed))
        } finally {
            passphrase.fill('\u0000')
        }
    }

    /** Imports with the passphrase saved on this device; this device keeps its own name, not the exporter's. */
    suspend fun importFrom(source: Uri): GitHubSyncSettingsTransferOutcome = withContext(dispatchers.io) {
        val current = settingsRepository.exportToMap(includeNonExportable = true)
        val currentPassphrase = current[SettingsRegistry.GithubSyncPassphrase.key].orEmpty().toCharArray()
        if (currentPassphrase.isEmpty()) return@withContext GitHubSyncSettingsTransferOutcome.MissingPassphrase
        try {
            importWith(source, currentPassphrase, keepDeviceName = current[SettingsRegistry.KindleDeviceName.key]?.trim()?.takeIf { it.isNotEmpty() })
        } finally {
            currentPassphrase.fill('\u0000')
        }
    }

    /**
     * For a device with no sync set up yet (onboarding): opens the file with the [passphrase] the reader typed. The
     * file carries the exporting device's name; [keepDeviceName] replaces it, so two devices never share a name.
     */
    suspend fun importFrom(source: Uri, passphrase: CharArray, keepDeviceName: String?): GitHubSyncSettingsTransferOutcome =
        withContext(dispatchers.io) {
            if (passphrase.isEmpty()) return@withContext GitHubSyncSettingsTransferOutcome.MissingPassphrase
            importWith(source, passphrase, keepDeviceName?.trim()?.takeIf { it.isNotEmpty() })
        }

    private suspend fun importWith(
        source: Uri,
        passphrase: CharArray,
        keepDeviceName: String?,
    ): GitHubSyncSettingsTransferOutcome = try {
        val input = context.contentResolver.openInputStream(source)
            ?: return GitHubSyncSettingsTransferOutcome.Failed(UiText.Res(R.string.settings_error_could_not_open_selected_file))
        val encrypted = input.use { it.readBytesLimited(MaxEncryptedTransferBytes) }
        val plaintext = try {
            cipher.decrypt(encrypted, passphrase, TransferAad)
        } catch (_: AEADBadTagException) {
            // AES-GCM can't tell a wrong passphrase from a damaged file, but the passphrase is by far the likelier.
            return GitHubSyncSettingsTransferOutcome.Failed(UiText.Res(R.string.sync_setup_error_wrong_passphrase))
        }
        failUnless(
            plaintext.size <= MaxPlaintextTransferBytes,
            R.string.settings_error_github_sync_settings_import_too_large,
        )
        val values = parsePlaintext(plaintext.toString(Charsets.UTF_8))
        settingsRepository.importFromMap(
            if (keepDeviceName == null) values else values + (SettingsRegistry.KindleDeviceName.key to keepDeviceName),
        )
        GitHubSyncSettingsTransferOutcome.Success
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (throwable: Throwable) {
        GitHubSyncSettingsTransferOutcome.Failed(throwable.uiText(R.string.settings_error_github_sync_settings_import_failed))
    }

    private fun buildPlaintext(settings: Map<String, String>): String {
        val syncSettings = JSONObject()
        GitHubSyncSettingKeys.forEach { key ->
            settings[key]?.let { syncSettings.put(key, it) }
        }
        return JSONObject()
            .put("format", "vayana.github-sync-settings")
            .put("formatVersion", TransferVersion)
            .put("exportedAt", System.currentTimeMillis())
            .put("settings", syncSettings)
            .toString()
    }

    private fun parsePlaintext(jsonText: String): Map<String, String> {
        failUnless(
            jsonText.length <= MaxPlaintextTransferBytes,
            R.string.settings_error_github_sync_settings_import_too_large,
        )
        val root = JSONObject(jsonText)
        failUnless(
            root.optString("format") == "vayana.github-sync-settings",
            R.string.settings_error_not_vayana_github_sync_settings_file,
        )
        failUnless(
            root.optInt("formatVersion") == TransferVersion,
            R.string.settings_error_unsupported_github_sync_settings_version,
        )
        val settings = root.getJSONObject("settings")
        return buildMap {
            GitHubSyncSettingKeys.forEach { key ->
                if (settings.has(key) && !settings.isNull(key)) {
                    val value = settings.optString(key)
                    failUnless(
                        value.length <= MaxSettingValueChars,
                        R.string.settings_error_github_sync_settings_value_too_large,
                    )
                    put(key, value)
                }
            }
        }
    }
}

private fun java.io.InputStream.readBytesLimited(maxBytes: Int): ByteArray {
    val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
    val output = java.io.ByteArrayOutputStream()
    var total = 0
    while (true) {
        val read = read(buffer)
        if (read == -1) break
        total += read
        failUnless(total <= maxBytes, R.string.settings_error_github_sync_settings_file_too_large)
        output.write(buffer, 0, read)
    }
    return output.toByteArray()
}

private val GitHubSyncSettingKeys = listOf(
    SettingsRegistry.GithubSyncEnabled.key,
    SettingsRegistry.KindleDeviceName.key,
    SettingsRegistry.GithubOwner.key,
    SettingsRegistry.GithubRepository.key,
    SettingsRegistry.GithubBranch.key,
    SettingsRegistry.GithubToken.key,
    SettingsRegistry.GithubSyncPassphrase.key,
)

private val TransferAad = "vayana.github-sync-settings.v1".toByteArray(Charsets.UTF_8)
private const val TransferVersion = 1
private const val MaxPlaintextTransferBytes = 64 * 1024
private const val MaxEncryptedTransferBytes = 128 * 1024
private const val MaxSettingValueChars = 1024
