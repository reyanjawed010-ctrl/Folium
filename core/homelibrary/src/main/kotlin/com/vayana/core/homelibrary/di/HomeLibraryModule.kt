package com.folium.core.homelibrary.di

import com.folium.core.diagnostics.DiagnosticCategory
import com.folium.core.diagnostics.DiagnosticsLogStore
import com.folium.core.homelibrary.ContentResolverHomeLibrarySource
import com.folium.core.homelibrary.DataStoreHomeLibraryCheckpointStore
import com.folium.core.homelibrary.HomeLibraryCheckpointStore
import com.folium.core.homelibrary.HomeLibrarySource
import com.folium.core.homelibrary.HomeLibraryStore
import com.folium.core.homelibrary.HomeLibrarySyncEngine
import com.folium.core.homelibrary.RoomHomeLibraryStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeLibraryModule {
    @Binds
    abstract fun bindSource(impl: ContentResolverHomeLibrarySource): HomeLibrarySource

    @Binds
    abstract fun bindStore(impl: RoomHomeLibraryStore): HomeLibraryStore

    @Binds
    abstract fun bindCheckpointStore(impl: DataStoreHomeLibraryCheckpointStore): HomeLibraryCheckpointStore

    companion object {
        @Provides
        @Singleton
        fun provideEngine(
            source: HomeLibrarySource,
            store: HomeLibraryStore,
            checkpoints: HomeLibraryCheckpointStore,
            logStore: DiagnosticsLogStore,
        ): HomeLibrarySyncEngine = HomeLibrarySyncEngine(
            source = source,
            store = store,
            checkpoints = checkpoints,
            log = { message -> logStore.record(DiagnosticCategory.SYNC, "HomeLibrarySync", message) },
        )
    }
}
