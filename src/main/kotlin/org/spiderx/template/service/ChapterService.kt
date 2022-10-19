package org.spiderx.template.service

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.spiderx.core.model.store.StoreChapter
import org.spiderx.template.db.service.IChapterService
import org.spiderx.template.model.DbStoreChapter
import org.spiderx.template.model.vo.ChapterV0
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChapterService {

    @Autowired
    lateinit var iChapterService: IChapterService

    fun getChaptersByBookId(id: Int): List<ChapterV0> {
        val query = QueryWrapper<DbStoreChapter>().eq(StoreChapter.Table.BOOKID, id)
        query.select(
            StoreChapter.Table.ID,
            StoreChapter.Table.BOOKID,
            StoreChapter.Table.NAME,
            StoreChapter.Table.LASTIME
        )
        query.orderByAsc(StoreChapter.Table.ID)
        return iChapterService.list(query).map {
            ChapterV0.fromDbStoreChapter(it)
        }
    }

    fun getReaderChapterById(id: Long): DbStoreChapter {
        return iChapterService.getById(id)
    }

    fun getPrevChapter(bookId: Long, currentChapterId: Long): ChapterV0 {
        val query = QueryWrapper<DbStoreChapter>()
            .eq(StoreChapter.Table.BOOKID, bookId)
            .select(
                StoreChapter.Table.ID,
                StoreChapter.Table.NAME
            )
            .lt(StoreChapter.Table.ID, currentChapterId).last("LIMIT 1")
            .orderByDesc(StoreChapter.Table.ID)

        return iChapterService.getOne(query).let {
            ChapterV0.fromDbStoreChapter(it)
        }
    }

    fun getNextChapter(bookId: Long, currentChapterId: Long): ChapterV0 {
        val query = QueryWrapper<DbStoreChapter>()
            .eq(StoreChapter.Table.BOOKID, bookId).select(
                StoreChapter.Table.ID,
                StoreChapter.Table.NAME
            )
            .gt(StoreChapter.Table.ID, currentChapterId).last("LIMIT 1")
            .orderByAsc(StoreChapter.Table.ID)

        return iChapterService.getOne(query).let {
            ChapterV0.fromDbStoreChapter(it)
        }
    }

}