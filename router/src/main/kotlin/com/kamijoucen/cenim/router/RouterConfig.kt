package com.kamijoucen.cenim.router

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component


@Component
@Configuration
@ConfigurationProperties("app.router")
class RouterConfig {
    var port: Int = -1
    var service: ArrayList<String> = ArrayList()
}