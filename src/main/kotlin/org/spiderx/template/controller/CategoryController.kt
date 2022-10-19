package org.spiderx.template.controller

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO
import org.spiderx.template.model.DbStoreBook
import org.spiderx.template.service.BookService
import org.spiderx.template.service.CategoryService
import org.spiderx.template.service.KtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class CategoryController {

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var bookService: BookService

    @GetMapping("/category/{id}_{page}.html")
    fun categoryBooks(@PathVariable id: Int, @PathVariable page: Int, model: Model): String {

        val multiCategory = categoryService.getCategoryList()
        val currentCategory = multiCategory.find {
            it.id?.toInt() == id
        }

        val pageDTO = bookService.queryBooks(id, null, Page<DbStoreBook>(page.toLong(), 18)) as Page

        model.addAttribute("currentCategory", currentCategory)
        model.addAttribute("multiCategory", multiCategory)
        model.addAttribute("content", pageDTO)
        model.addAttribute("categoryService", categoryService)
        model.addAttribute("bookService", bookService)
        model.addAttribute("kts", KtService())
        return "category"
    }
}