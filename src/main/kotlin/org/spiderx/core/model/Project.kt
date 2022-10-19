package org.spiderx.core.model

/**
 * @param id 规则ID
 * @param name 采集规则名称
 * @param author 规则开发作者
 * @param version 版本号
 * @param url 采集源网址
 */
data class Project(
    val id: String,
    val name: String,
    val author: String,
    val version: String,
    val url: String,
    val threads: Int
) {
    companion object {
        fun from(data: Map<String, Any>): Project {
            return Project(
                data["id"] as String,
                data["name"] as String,
                runCatching { data["author"] as String }.getOrDefault(""),
                data["version"] as String,
                data["url"] as String,
                runCatching { data["threads"] as Int }.getOrDefault(0)
            )
        }
    }
}
