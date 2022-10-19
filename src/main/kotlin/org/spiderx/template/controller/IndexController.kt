package org.spiderx.template.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.spiderx.core.model.store.StoreChapter
import org.spiderx.template.model.DbStoreBook
import org.spiderx.template.model.DbStoreChapter
import org.spiderx.template.model.vo.CategoryV0
import org.spiderx.template.service.BookService
import org.spiderx.template.service.CategoryService
import org.spiderx.template.service.ChapterService
import org.spiderx.template.service.KtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@Controller
class IndexController {

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var bookService: BookService

    @Autowired
    lateinit var chapterService: ChapterService

    @GetMapping("/")
    fun index(response: HttpServletResponse, model: Model): String {

        val multiCategory = categoryService.getCategoryList()

        model.addAttribute("multiCategory", multiCategory)
        model.addAttribute("categoryService", categoryService)
        model.addAttribute("bookService", bookService)
        model.addAttribute("kts", KtService)

        return "index"
    }

    @GetMapping("/search")
    fun search(keyword: String, @RequestParam(defaultValue = "1") page: Int, model: Model): String {

        val pageDTO = bookService.queryBooksByKeyword(keyword, Page<DbStoreBook>(page.toLong(), 18)) as Page

        if (pageDTO.total == 1L) {
            pageDTO.records.singleOrNull()?.let {
                return "redirect:/book/${it.id}.html"
            }
        }
        model.addAttribute("keyword", keyword)
        model.addAttribute("content", pageDTO)

        return "search"
    }

    @GetMapping("/book/{id}.html")
    fun bookDetail(@PathVariable id: Int, model: Model): String {
        if (id <= 0) return "redirect:/"
        val book = kotlin.runCatching {
            bookService.queryBookById(id)
        }.getOrNull() ?: return "redirect:/"

        // 查询章节列表
        val category = kotlin.runCatching {
            categoryService.getCategoryById(book.categoryId!!)
        }.getOrDefault(CategoryV0().apply {
            this.id = 0
            this.name = "默认分类"
        })
        val chapters = kotlin.runCatching {
            chapterService.getChaptersByBookId(book.id!!.toInt())
        }.getOrDefault(emptyList())

        model.addAttribute("chapters", chapters)
        model.addAttribute("book", book)
        model.addAttribute("category", category)

        return "book"
    }


    @GetMapping("/test")
    @ResponseBody
    fun test():Any{
        // data is null
        println(chapterService.iChapterService.getById(771489998674329601))
        return "aa"
    }

}