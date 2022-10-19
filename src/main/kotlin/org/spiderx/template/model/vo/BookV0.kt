package org.spiderx.template.model.vo

import org.spiderx.template.model.DbStoreBook
import org.spiderx.template.model.vpo.BookVP0


open class BookV0 {
    open var id: Long? = null
    open var name: String? = null
    open var author: String? = null
    open var status: Int = 0
    open var thumb: String? = null
    open var intro: String? = null
    open var lastime: Long? = null
    open var categoryId: Int? = null
    open var category: CategoryV0? = null

    companion object {

        val regex = Regex("<[^>]+>")

        fun fromDbStoreBook(dbStoreBook: DbStoreBook, thumbHost: String? = ""): BookV0 {
            return BookV0().apply {
                id = dbStoreBook.id
                name = dbStoreBook.name
                author = dbStoreBook.author
                status = dbStoreBook.status
                if (!dbStoreBook.thumb.isNullOrEmpty()) {
                    thumb = "${thumbHost}/thumb${dbStoreBook.thumb}"
                }
                categoryId = dbStoreBook.categoryId
                intro = dbStoreBook.intro?.trim()?.replace(regex,"")
                lastime = dbStoreBook.lastime
            }
        }

        fun fromBookVP0(bookVP0: BookVP0, thumbHost: String? = ""): BookV0 {
            return BookV0().apply {
                id = bookVP0.id
                name = bookVP0.name
                author = bookVP0.author
                status = bookVP0.status
                if (!bookVP0.thumb.isNullOrEmpty()) {
                    thumb = "${thumbHost}/thumb${bookVP0.thumb}"
                }
                category = CategoryV0().apply {
                    id = bookVP0.categoryId
                    name = bookVP0.categoryName
                }
                categoryId = bookVP0.categoryId
                intro = bookVP0.intro?.trim()
                lastime = bookVP0.lastime
            }
        }
    }
}