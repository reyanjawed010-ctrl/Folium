package com.folium.core.database.di

import com.folium.core.database.repository.AnnotationRepository
import com.folium.core.database.repository.AnnotationRepositoryImpl
import com.folium.core.database.repository.BookRepository
import com.folium.core.database.repository.BookRepositoryImpl
import com.folium.core.database.repository.HighlightReviewRepository
import com.folium.core.database.repository.HighlightReviewRepositoryImpl
import com.folium.core.database.repository.ReadingSessionRepository
import com.folium.core.database.repository.ReadingSessionRepositoryImpl
import com.folium.core.database.repository.ShelfRepository
import com.folium.core.database.repository.ShelfRepositoryImpl
import com.folium.core.database.repository.VocabularyCardRepository
import com.folium.core.database.repository.VocabularyCardRepositoryImpl
import com.folium.core.database.repository.WordLookupStatRepository
import com.folium.core.database.repository.WordLookupStatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindBookRepository(impl: BookRepositoryImpl): BookRepository

    @Binds
    @Singleton
    abstract fun bindAnnotationRepository(impl: AnnotationRepositoryImpl): AnnotationRepository

    @Binds
    @Singleton
    abstract fun bindWordLookupStatRepository(impl: WordLookupStatRepositoryImpl): WordLookupStatRepository

    @Binds
    @Singleton
    abstract fun bindReadingSessionRepository(impl: ReadingSessionRepositoryImpl): ReadingSessionRepository

    @Binds
    @Singleton
    abstract fun bindShelfRepository(impl: ShelfRepositoryImpl): ShelfRepository

    @Binds
    @Singleton
    abstract fun bindVocabularyCardRepository(impl: VocabularyCardRepositoryImpl): VocabularyCardRepository

    @Binds
    @Singleton
    abstract fun bindHighlightReviewRepository(impl: HighlightReviewRepositoryImpl): HighlightReviewRepository
}
