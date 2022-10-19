import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "org.spiderx"
version = "1.0-final"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }

    // 查询数据库
    runtimeOnly("mysql:mysql-connector-java")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
    implementation("com.baomidou:mybatis-plus-generator:3.5.3")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.2")

    implementation("org.apache.shardingsphere:shardingsphere-jdbc-core-spring-boot-starter:5.2.0") {
        exclude("com.squareup.okhttp3", "okhttp")
        exclude("com.google.protobuf", "protobuf-java")
        exclude("log4j", "log4j")
        exclude("org.apache.calcite", "calcite-core")
    }
    // 修复 shardingsphere-jdbc 依赖漏洞
    implementation("com.google.protobuf:protobuf-java:3.21.6")
    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
    implementation("org.apache.calcite:calcite-core:1.32.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
