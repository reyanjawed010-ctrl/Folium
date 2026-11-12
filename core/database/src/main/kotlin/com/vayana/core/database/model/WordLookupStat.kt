package com.folium.core.database.model

data class WordLookupStat(
    val word: String,
    val count: Int,
    val lastLookedUpAt: Long,
)
