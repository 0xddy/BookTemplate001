package org.spiderx.core.model.store


open class StoreChapter {
    open var id: Long? = null
    open var name: String? = null
    open var bookId: Long? = null
    open var content: ByteArray? = null
    open var lastime: Long? = null

    class Table {
        companion object {
            const val ID = "id"
            const val NAME = "`name`"
            const val BOOKID = "book_id"
            const val CONTENT = "content"
            const val LASTIME = "lastime"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is StoreChapter) {
            return name == other.name
                    && bookId == other.bookId
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (bookId?.hashCode() ?: 0)
        return result
    }

}
