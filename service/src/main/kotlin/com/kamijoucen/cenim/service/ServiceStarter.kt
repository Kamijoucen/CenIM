package com.kamijoucen.cenim.service

import com.kamijoucen.cenim.common.util.ContextUtil
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.kamijoucen.cenim"])
class CenImServiceApp


fun main(args: Array<String>) {
    runApplication<CenImServiceApp>(*args)
    // 加载配置
    val config = ContextUtil.getBean(ServiceConfig::class.java)
    // 启动服务器
    val serverSuccess = startServiceServer(config)
    println(config)
}
