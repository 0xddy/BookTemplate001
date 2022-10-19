package org.spiderx.template.controller

import org.spiderx.template.service.BookService
import org.spiderx.template.service.ChapterService
import org.spiderx.template.service.KtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ReaderController {

    @Autowired
    lateinit var bookService: BookService

    @Autowired
    lateinit var chapterService: ChapterService


    @GetMapping("/read/{bookId}/{id}.html")
    fun read(@PathVariable bookId: Long, @PathVariable id: Long, model: Model): String {
        if (bookId <= 0 || id <= 0) {
            return "redirect:/"
        }
        val book = kotlin.runCatching {
            bookService.getBookV0ById(bookId)
        }.getOrNull() ?: return "redirect:/"

        val readerChapter = kotlin.runCatching {
            chapterService.getReaderChapterById(id)
        }.getOrNull() ?: return "redirect:/"

        val prevChapter = kotlin.runCatching {
            chapterService.getPrevChapter(bookId, id)
        }.getOrNull()

        val nextChapter = kotlin.runCatching {
            chapterService.getNextChapter(bookId, id)
        }.getOrNull()

        model.addAttribute("book", book)
        model.addAttribute("readerChapter", readerChapter)
        model.addAttribute("prevChapter",prevChapter)
        model.addAttribute("nextChapter",nextChapter)
        model.addAttribute("kts", KtService)

        return "reader"
    }

}