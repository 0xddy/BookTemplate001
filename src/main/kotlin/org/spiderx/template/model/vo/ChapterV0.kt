package org.spiderx.template.model.vo

import org.spiderx.template.model.DbStoreChapter

class ChapterV0 {
    var id: Long? = null
    var name: String? = null
    var bookId: Long? = null
    var lastime: Long? = null

    companion object {
        fun fromDbStoreChapter(dbStoreChapter: DbStoreChapter): ChapterV0 {
            return ChapterV0().apply {
                id = dbStoreChapter.id
                name = dbStoreChapter.name
                bookId = dbStoreChapter.bookId
                lastime = dbStoreChapter.lastime
            }
        }
    }
}