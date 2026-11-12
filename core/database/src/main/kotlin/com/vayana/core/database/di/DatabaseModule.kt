package com.folium.core.database.di

import android.content.Context
import androidx.room.Room
import com.folium.core.database.ALL_MIGRATIONS
import com.folium.core.database.VayanaDatabase
import com.folium.core.database.dao.AnnotationDao
import com.folium.core.database.dao.BookAliasDao
import com.folium.core.database.dao.BookDao
import com.folium.core.database.dao.ReadingSessionDao
import com.folium.core.database.dao.ShelfDao
import com.folium.core.database.dao.TombstoneDao
import com.folium.core.database.dao.VocabularyCardDao
import com.folium.core.database.dao.WordLookupStatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.folium.core.database.search.BookSearchIndexCallback
import com.folium.core.database.dao.HighlightReviewDao
import com.folium.core.database.dao.FullSyncStateDao
import com.folium.core.database.dao.SyncedSnapshotSliceDao
import com.folium.core.database.dao.PendingCloudDeletionDao
import com.folium.core.database.sync.FullSyncChangeTrackerCallback

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideVayanaDatabase(@ApplicationContext context: Context): VayanaDatabase =
        Room.databaseBuilder(context, VayanaDatabase::class.java, "vayana.db")
            .addMigrations(*ALL_MIGRATIONS)
            .addCallback(BookSearchIndexCallback)
            .addCallback(com.folium.core.database.search.EpubPassageIndexCallback)
            .addCallback(FullSyncChangeTrackerCallback)
            .build()

    @Provides
    fun provideEpubPassageDao(database: VayanaDatabase): com.folium.core.database.dao.EpubPassageDao = database.epubPassageDao()

    @Provides
    fun provideBookDao(database: VayanaDatabase): BookDao = database.bookDao()

    @Provides
    fun provideAnnotationDao(database: VayanaDatabase): AnnotationDao = database.annotationDao()

    @Provides
    fun provideWordLookupStatDao(database: VayanaDatabase): WordLookupStatDao = database.wordLookupStatDao()

    @Provides
    fun provideReadingSessionDao(database: VayanaDatabase): ReadingSessionDao = database.readingSessionDao()

    @Provides
    fun provideShelfDao(database: VayanaDatabase): ShelfDao = database.shelfDao()

    @Provides
    fun provideVocabularyCardDao(database: VayanaDatabase): VocabularyCardDao = database.vocabularyCardDao()

    @Provides
    fun provideBookAliasDao(database: VayanaDatabase): BookAliasDao = database.bookAliasDao()

    @Provides
    fun provideTombstoneDao(database: VayanaDatabase): TombstoneDao = database.tombstoneDao()

    @Provides
    fun providePendingCloudDeletionDao(database: VayanaDatabase): PendingCloudDeletionDao = database.pendingCloudDeletionDao()

    @Provides
    fun provideHighlightReviewDao(database: VayanaDatabase): HighlightReviewDao = database.highlightReviewDao()

    @Provides
    fun provideFullSyncStateDao(database: VayanaDatabase): FullSyncStateDao = database.fullSyncStateDao()

    @Provides
    fun provideSyncedSnapshotSliceDao(database: VayanaDatabase): SyncedSnapshotSliceDao = database.syncedSnapshotSliceDao()
}
