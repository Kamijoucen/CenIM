package com.kamijoucen.cenim.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("app.service")
class ServiceConfig {
    var port: Int = -1
}