package org.spiderx.template.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment


@Configuration
class MyBatisPlusConfig {
    @Autowired
    lateinit var env: Environment

    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val springDriverClassName = env.getProperty("spring.datasource.driver-class-name", "")
        val shardDriverClassName = env.getProperty("spring.shardingsphere.datasource.master.driver-class-name", "")

        var dbType: DbType? = null

        val driveName = springDriverClassName.ifEmpty {
            shardDriverClassName
        }
        when (driveName) {
            "org.mariadb.jdbc.Driver" -> {
                dbType = DbType.MARIADB
            }
            "com.mysql.cj.jdbc.Driver" -> {
                dbType = DbType.MYSQL
            }
            "org.postgresql.Driver" -> {
                dbType = DbType.POSTGRE_SQL
            }
        }
        val interceptor = MybatisPlusInterceptor()
        interceptor.addInnerInterceptor(PaginationInnerInterceptor(dbType).apply {
            isOptimizeJoin = true
        })

        return interceptor
    }

}