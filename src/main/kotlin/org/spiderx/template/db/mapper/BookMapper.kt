package org.spiderx.template.db.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Select
import org.spiderx.template.model.DbStoreBook
import org.spiderx.template.model.DbStoreCategory
import org.spiderx.template.model.vpo.BookVP0

interface BookMapper : BaseMapper<DbStoreBook> {
    @Select(
        """
           <script>
                SELECT ${DbStoreBook.Table.ID},
                        ${DbStoreBook.Table.NAME},
                        ${DbStoreBook.Table.AUTHOR},
                        ${DbStoreBook.Table.THUMB},
                        ${DbStoreBook.Table.INTRO},
                        ${DbStoreBook.Table.STATUS},
                        ${DbStoreBook.Table.LASTIME},
                        ${DbStoreCategory.Table.ID} as categoryId,
                        ${DbStoreCategory.Table.NAME} as categoryName,
                        ${DbStoreBook.Table.THUMB_STATE} as thumbState,
                        ${DbStoreBook.Table.STATUS_STATE} as statusState
                FROM ${DbStoreBook.TABLE_NAME} 
                    LEFT JOIN ${DbStoreCategory.TABLE_NAME} 
                    ON ${DbStoreBook.Table.CATEGORY_ID} = ${DbStoreCategory.Table.ID}
                <where>
                    ${DbStoreBook.Table.ID} = #{id} 
                </where>
            </script>
        """
    )
    fun getBookById(id: Long): BookVP0

}