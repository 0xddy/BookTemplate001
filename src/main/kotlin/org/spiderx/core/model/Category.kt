package org.spiderx.core.model
/**
 * @param id 分类ID
 * @param title 分类标题
 * @param page  采集分类列表页码范围
 * @param post 发布对应源库分类ID
 */
data class Category(
    val id: Int,
    val title: String,
    val alias: String,
    val page: CategoryPage,
    val post: Int
) {

    companion object {

        fun from(data: Map<String, Any>): Category {
            val pageMap = data["page"] as Map<*, *>

            var alias = ""
            if (data["alias"] !== null) {
                alias = data["alias"] as String
            }

            return Category(
                data["id"] as Int,
                data["title"] as String,
                alias,
                CategoryPage(
                    pageMap["start"] as Int,
                    pageMap["end"] as Int
                ),
                data["post"] as Int
            )
        }
    }

}
