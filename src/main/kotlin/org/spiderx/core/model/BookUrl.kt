package org.spiderx.core.model

/**
 * 采集到的书籍源url信息书籍
 *
 * @param name 书籍名称
 * @param url 书籍源地址
 * @param author 作者
 * @param status 完结状态
 *  @sample
 *      0 未知
 *      1 连载
 *      2 完结
 */
data class BookUrl(
    val name: String,
    val url: String,
    val author: String,
    val status: Int
) {
    companion object {

        fun from(data: Map<String, Any>): BookUrl {
            return BookUrl(
                data["name"] as String,
                data["url"] as String,
                data["author"] as String,
                data["status"] as Int
            )
        }
    }
}
