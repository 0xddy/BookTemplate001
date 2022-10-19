package org.spiderx.template.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.spiderx.core.model.store.StoreCategory
import org.spiderx.template.model.DbStoreCategory.Companion.TABLE_NAME

@TableName(value = TABLE_NAME)
open class DbStoreCategory : StoreCategory() {
    @TableId(value = "id", type = IdType.AUTO)
    override var id: Long? = null

    companion object {
        const val TABLE_NAME = "category"
    }

    class Table{
        companion object{
            const val ID = "$TABLE_NAME.id"
            const val NAME = "$TABLE_NAME.`name`"
        }
    }
}