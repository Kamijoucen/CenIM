package com.kamijoucen.cenim.connector

import com.kamijoucen.cenim.common.util.SpringUtil
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.kamijoucen.cenim"])
class CenImApplication


fun main(args: Array<String>) {
    runApplication<CenImApplication>(*args)

    // 加载配置
    val config = SpringUtil.getBean(ConnectorConfig::class.java)
    //
    // 启动服务器
    val success = start(config)

    println(config)
}
