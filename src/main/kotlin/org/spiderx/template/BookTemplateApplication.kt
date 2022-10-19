package org.spiderx.template

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@MapperScan("org.spiderx.template.db.mapper")
@EnableTransactionManagement
class BookTemplateApplication

fun main(args: Array<String>) {
    runApplication<BookTemplateApplication>(*args)
}
