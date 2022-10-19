package org.spiderx.core.model.store

open class StoreBook {
    open var id: Long? = null
    open var name: String? = null
    open var author: String? = null
    open var status: Int = 0
    open var categoryId: Int = 0
    open var thumb: String? = null
    open var intro: String? = null
    open var lastime: Long? = null
    open var thumbState: Boolean = false
    open var statusState: Boolean = false

    class Table {
        companion object {
            const val ID = "id"
            const val NAME = "name"
            const val AUTHOR = "author"
            const val STATUS = "status"
            const val STATUS_STATE = "status_state"
            const val CATEGORY_ID = "category_id"
            const val THUMB = "thumb"
            const val THUMB_STATE = "thumb_state"
            const val INTRO = "intro"
            const val LASTIME = "lastime"
        }
    }

}