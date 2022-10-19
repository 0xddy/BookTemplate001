package org.spiderx.template.db.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.spiderx.core.model.store.StoreCategory
import org.spiderx.template.db.mapper.CategoryMapper
import org.spiderx.template.model.DbStoreCategory
import org.spiderx.template.db.service.ICategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl : ServiceImpl<CategoryMapper, DbStoreCategory>(), ICategoryService {
}