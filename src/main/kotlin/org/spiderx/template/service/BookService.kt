package org.spiderx.template.service

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO
import org.spiderx.template.db.service.IBookService
import org.spiderx.template.model.DbStoreBook
import org.spiderx.template.model.vo.BookV0
import org.spiderx.template.model.vpo.BookVP0
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class BookService {

    @Value("\${spiderx.data-api.thumb.host}")
    lateinit var thumbHost: String

    @Autowired
    lateinit var iBookService: IBookService

    fun getTopBook(ids: Collection<Int>): List<BookV0> {
        println(ids)
        return getTopBook(ids, null)
    }

    fun getTopBook(ids: Collection<Int>, category: Int?): List<BookV0> {
        val query = QueryWrapper<DbStoreBook>().`in`(DbStoreBook.Table.ID, ids)
        category?.let {
            query.eq(DbStoreBook.Table.CATEGORY_ID, category)
        }
        var multiBook = iBookService.list(query)
        // 简单排个序
        ids.mapNotNull {
            multiBook.find { book ->
                book.id!!.toInt() == it
            }
        }.let {
            multiBook = it
        }

        return multiBook.map { dbStoreBook ->
            BookV0.fromDbStoreBook(dbStoreBook, thumbHost)
        }
    }

    fun queryBooks(category: Int?, keyword: String?, iPage: Page<DbStoreBook>): IPage<BookV0> {
        val query = QueryWrapper<DbStoreBook>()
        category?.let {
            query.eq(DbStoreBook.Table.CATEGORY_ID, category)
            query.orderByDesc(DbStoreBook.Table.ID)
        }
        keyword?.let {
            query.like(DbStoreBook.Table.NAME, keyword)
            query.orderByAsc("LENGTH(${DbStoreBook.Table.NAME})")
        }

        val pageDTO = iBookService.page(iPage, query)
        return pageDTO.convert { dbStoreBook ->
            BookV0.fromDbStoreBook(dbStoreBook, thumbHost)
        }
    }

    fun queryBooksByKeyword(keyword: String, iPage: Page<DbStoreBook>): IPage<BookV0> {
        return queryBooks(null, keyword, iPage)
    }

    fun queryBookById(id: Int): BookV0 {
        return BookV0.fromDbStoreBook(iBookService.getById(id), thumbHost)
    }

    fun getBookV0ById(id: Long): BookV0 {
        return BookV0.fromBookVP0(iBookService.getBookById(id))
    }
}