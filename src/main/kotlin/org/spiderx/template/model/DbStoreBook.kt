package org.spiderx.template.model

import com.baomidou.mybatisplus.annotation.*
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.spiderx.core.model.store.StoreBook
import org.spiderx.template.model.DbStoreBook.Companion.TABLE_NAME

@TableName(value = TABLE_NAME)
open class DbStoreBook : StoreBook() {

    @JsonSerialize(using = ToStringSerializer::class)
    @TableId(value = "id", type = IdType.AUTO)
    override var id: Long? = null

    @TableField(fill = FieldFill.INSERT)
    override var lastime: Long? = null

    companion object {
        const val TABLE_NAME = "book"
    }

    class Table {
        companion object {
            const val ID = "$TABLE_NAME.id"
            const val NAME = "$TABLE_NAME.`name`"
            const val AUTHOR = "$TABLE_NAME.author"
            const val STATUS = "$TABLE_NAME.`status`"
            const val STATUS_STATE = "$TABLE_NAME.status_state"
            const val CATEGORY_ID = "$TABLE_NAME.category_id"
            const val THUMB = "$TABLE_NAME.thumb"
            const val INTRO = "$TABLE_NAME.intro"
            const val LASTIME = "$TABLE_NAME.lastime"
            const val THUMB_STATE = "$TABLE_NAME.thumb_state"
        }
    }
}
