package org.spiderx.core.model

/**
 * 采集解析的模型
 */
data class Book(
    val name: String,
    val author: String,
    val cover: String,
    val intro: String,
    val status: Int,
    val lastime: Long
) {
    companion object {

        fun from(data: Map<String, Any>): Book {
            return Book(
                data["name"] as String,
                data["author"] as String,
                data["cover"] as String,
                data["intro"] as String,
                runCatching { data["status"] as Int }.getOrDefault(0),
                runCatching {
                    // 校验时间戳长度
                    (data["lastime"] as Long).let {
                        if (it.toString().length == 13) {
                            it / 1000
                        } else {
                            it
                        }
                    }
                }.getOrDefault(System.currentTimeMillis() / 1000)
            )
        }

    }
}
