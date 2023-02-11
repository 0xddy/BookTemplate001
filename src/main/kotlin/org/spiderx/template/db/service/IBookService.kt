package org.spiderx.template.db.service

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.IService
import org.spiderx.template.model.DbStoreBook
import org.spiderx.template.model.vpo.BookVP0

interface IBookService : IService<DbStoreBook> {
    fun getBookById(id: Long): BookVP0
    fun getRandBook(category: Int?, size: Int): List<DbStoreBook>
}