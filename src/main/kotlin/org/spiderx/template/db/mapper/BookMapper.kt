package org.spiderx.template.db.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
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

    @Select(
        """
            <script>
                SELECT * FROM ${DbStoreBook.TABLE_NAME}
                <where>
                    <if test="category != null">
                        ${DbStoreBook.Table.CATEGORY_ID} = #{category} 
                    </if>
                </where> 
                ORDER BY RAND() LIMIT #{size}
            </script>
        """
    )
    fun getRandBook(category: Int?, size: Int = 10): List<DbStoreBook>
}