package org.spiderx.template.model

import com.baomidou.mybatisplus.annotation.*
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.spiderx.core.model.store.StoreChapter
import org.spiderx.template.config.UnzipStringSerializer
import org.spiderx.template.model.DbStoreChapter.Companion.TABLE_NAME

@TableName(TABLE_NAME)
class DbStoreChapter : StoreChapter() {

    @JsonSerialize(using = ToStringSerializer::class)
    @TableId(value = "id", type = IdType.AUTO)
    override var id: Long? = null

    @JsonSerialize(using = UnzipStringSerializer::class)
    override var content: ByteArray? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    override var lastime: Long? = null

    companion object {
        const val TABLE_NAME = "chapter"
    }

}