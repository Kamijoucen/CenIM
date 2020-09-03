package com.kamijoucen.cenim.router

import com.kamijoucen.cenim.common.util.ContextUtil
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.kamijoucen.cenim"])
class CenImRouterApp


fun main(args: Array<String>) {
    runApplication<CenImRouterApp>(*args)
    // 加载配置
    val config = ContextUtil.getBean(RouterConfig::class.java)
    // 链接 service 层
    var clientSuccess = startRouterClient(config)
    // 启动服务器
    val serverSuccess = startRouterServer(config)
    println(config)
}
