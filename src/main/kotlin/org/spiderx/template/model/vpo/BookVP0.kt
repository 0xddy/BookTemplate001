package org.spiderx.template.model.vpo

open class BookVP0 {
    open var id: Long? = null
    open var name: String? = null
    open var author: String? = null
    open var status: Int = 0

    open var thumb: String? = null
    open var intro: String? = null
    open var lastime: Long? = null

    open var categoryId: Int? = null
    open var categoryName: String? = null

    open var thumbState: Boolean =  false
    open var statusState:Boolean = false

}