package org.spiderx.core.model.store

open class StoreCategory {
    open  var id: Long? = null
    open var name: String? = null

    class Table{
        companion object{
            const val ID = "id"
            const val NAME = "`name`"
        }
    }
}