package org.spiderx.template.db.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.spiderx.template.db.mapper.BookMapper
import org.spiderx.template.db.service.IBookService
import org.spiderx.template.model.DbStoreBook
import org.spiderx.template.model.vpo.BookVP0
import org.springframework.stereotype.Service

@Service
class BookServiceImpl : ServiceImpl<BookMapper, DbStoreBook>(), IBookService {
    override fun getBookById(id: Long): BookVP0 {
        return baseMapper.getBookById(id)
    }

    override fun getRandBook(category: Int?, size: Int): List<DbStoreBook> {
        return baseMapper.getRandBook(category, size)
    }
}