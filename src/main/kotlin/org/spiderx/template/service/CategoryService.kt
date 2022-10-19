package org.spiderx.template.service

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.spiderx.core.model.store.StoreCategory
import org.spiderx.template.db.service.ICategoryService
import org.spiderx.template.model.DbStoreBook
import org.spiderx.template.model.DbStoreCategory
import org.spiderx.template.model.vo.CategoryV0
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService {

    @Autowired
    lateinit var iCategoryService: ICategoryService

    fun getCategoryList(): List<DbStoreCategory> {
        val query = QueryWrapper<DbStoreCategory>().orderByAsc(StoreCategory.Table.ID)
        return iCategoryService.list(query)
    }

    fun getCategoryById(id: Int): CategoryV0 {
        return iCategoryService.getById(id).let {
            val categoryV0 = CategoryV0()
            categoryV0.id = it.id!!.toInt()
            categoryV0.name = it.name
            categoryV0
        }
    }


}