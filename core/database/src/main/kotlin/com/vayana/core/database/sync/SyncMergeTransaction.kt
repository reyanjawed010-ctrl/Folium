package com.folium.core.database.sync

import androidx.room.withTransaction
import com.folium.core.database.VayanaDatabase
import javax.inject.Inject

/** Runs a batch of cloud merges as one transaction; repository merges called inside join it. */
class SyncMergeTransaction @Inject constructor(private val database: VayanaDatabase) {
    suspend fun <R> run(block: suspend () -> R): R = database.withTransaction(block)
}
