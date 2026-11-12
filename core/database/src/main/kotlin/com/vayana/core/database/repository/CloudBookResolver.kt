package com.folium.core.database.repository

import com.folium.core.database.dao.BookAliasDao
import com.folium.core.database.dao.BookDao
import com.folium.core.database.entity.BookEntity

internal suspend fun BookDao.findActiveBySyncIdOrAlias(
    syncId: String,
    bookAliasDao: BookAliasDao,
): BookEntity? {
    findBySyncId(syncId)?.let { return it }
    val alias = bookAliasDao.findBySyncId(syncId) ?: return null
    return findByHash(alias.fileHash)
}

internal fun shelfMembershipTombstoneSyncId(bookSyncId: String, shelfSyncId: String): String =
    "shelf_membership:$bookSyncId:$shelfSyncId"
