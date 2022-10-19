package org.spiderx.template.db.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.spiderx.template.db.mapper.ChapterMapper
import org.spiderx.template.db.service.IChapterService
import org.spiderx.template.model.DbStoreChapter
import org.springframework.stereotype.Service

@Service
class ChapterServiceImpl : ServiceImpl<ChapterMapper, DbStoreChapter>(), IChapterService {
}