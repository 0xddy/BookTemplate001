package org.spiderx.core.model

/**
 * 存储需要采集章节url信息
 */
data class ChapterUrl(
    val title: String,
    val url: String
) {

    @JvmName("getTitle2")
    fun getTitle():String{
        return title
    }

    companion object {

        fun from(map: Map<String, Any>): ChapterUrl {

            return ChapterUrl(
                map["title"] as String,
                map["url"] as String
            )
        }

    }
}
