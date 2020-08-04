package com.kamijoucen.cenim.connector


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CenImApplication

fun main(args: Array<String>) {
    runApplication<CenImApplication>(*args)

    // 加载配置
    val parseConfig = parseConfig()
    // 启动服务器
    val success = start(parseConfig)


    println(parseConfig)



    Thread.sleep(10000)
}
