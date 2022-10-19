package org.spiderx.core.model.store


open class StoreBookurl {

    open var id: Long? = null
    open var name: String? = null
    open var author: String? = null
    open var status: Int = 0
    open var url: String? = null
    open var projectId: String? = null
    open var post: Int = 0

    enum class State {
        Serialization,
        Over,
        Unknown,
        SerializationAndUnknown,
        ALL
    }

    class Table {
        companion object {
            // table field name
            const val ID = "id"
            const val NAME = "`name`"
            const val AUTHOR = "author"
            const val STATUS = "`status`"
            const val URL = "url"
            const val PROJECTID = "project_id"
            const val POST = "post"
        }
    }
}
