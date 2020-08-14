package com.kamijoucen.cenim.transfer

import com.kamijoucen.cenim.common.util.ContextUtil
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.kamijoucen.cenim"])
class CenImApplication


fun main(args: Array<String>) {
    runApplication<CenImApplication>(*args)
    // 加载配置
    val config = ContextUtil.getBean(TransferConfig::class.java)
    // 链接 transfer 层
    var clientSuccess = startTransferClient(config)
    // 启动服务器
    val serverSuccess = startTransferServer(config)
    println(config)
}
