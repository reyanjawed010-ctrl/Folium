package com.folium.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.folium.core.database.dao.AnnotationDao
import com.folium.core.database.dao.BookAliasDao
import com.folium.core.database.dao.BookDao
import com.folium.core.database.dao.HighlightReviewDao
import com.folium.core.database.dao.FullSyncStateDao
import com.folium.core.database.dao.SyncedSnapshotSliceDao
import com.folium.core.database.dao.ReadingSessionDao
import com.folium.core.database.dao.ShelfDao
import com.folium.core.database.dao.TombstoneDao
import com.folium.core.database.dao.VocabularyCardDao
import com.folium.core.database.dao.WordLookupStatDao
import com.folium.core.database.entity.AnnotationEntity
import com.folium.core.database.entity.AnnotationFtsEntity
import com.folium.core.database.entity.BookAliasEntity
import com.folium.core.database.entity.BookEntity
import com.folium.core.database.entity.BookFtsEntity
import com.folium.core.database.entity.BookShelfCrossRefEntity
import com.folium.core.database.entity.HighlightReviewEntity
import com.folium.core.database.entity.FullSyncStateEntity
import com.folium.core.database.entity.SyncedSnapshotSliceEntity
import com.folium.core.database.entity.ReadingSessionEntity
import com.folium.core.database.entity.ShelfEntity
import com.folium.core.database.entity.TombstoneEntity
import com.folium.core.database.entity.VocabularyCardEntity
import com.folium.core.database.entity.WordLookupStatEntity
import com.folium.core.database.dao.PendingCloudDeletionDao
import com.folium.core.database.entity.PendingCloudDeletionEntity

/** Bumping this is a real Room migration + a docs/DATABASE_CHANGELOG.md entry, from day one (docs/PRODUCT_SPEC.md §2). */
const val DATABASE_VERSION = 41

@Database(
    entities = [
        BookEntity::class, AnnotationEntity::class, WordLookupStatEntity::class, ReadingSessionEntity::class,
        ShelfEntity::class, BookShelfCrossRefEntity::class, VocabularyCardEntity::class,
        BookAliasEntity::class, TombstoneEntity::class, BookFtsEntity::class, AnnotationFtsEntity::class,
        PendingCloudDeletionEntity::class, HighlightReviewEntity::class,
        FullSyncStateEntity::class,
        com.folium.core.database.entity.EpubChapterEntity::class,
        com.folium.core.database.entity.EpubPassageEntity::class,
        com.folium.core.database.entity.EpubPassageTextEntity::class,
        SyncedSnapshotSliceEntity::class,
        com.folium.core.database.entity.SyncSliceVersionEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = true,
)
abstract class VayanaDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun annotationDao(): AnnotationDao
    abstract fun wordLookupStatDao(): WordLookupStatDao
    abstract fun readingSessionDao(): ReadingSessionDao
    abstract fun shelfDao(): ShelfDao
    abstract fun vocabularyCardDao(): VocabularyCardDao
    abstract fun bookAliasDao(): BookAliasDao
    abstract fun tombstoneDao(): TombstoneDao
    abstract fun pendingCloudDeletionDao(): PendingCloudDeletionDao
    abstract fun highlightReviewDao(): HighlightReviewDao
    abstract fun epubPassageDao(): com.folium.core.database.dao.EpubPassageDao
    abstract fun fullSyncStateDao(): FullSyncStateDao
    abstract fun syncedSnapshotSliceDao(): SyncedSnapshotSliceDao
}
