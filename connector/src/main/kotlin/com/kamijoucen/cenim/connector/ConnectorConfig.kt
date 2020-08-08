package com.kamijoucen.cenim.connector

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("app.connector")
class ConnectorConfig {
    var port: Int = -1
}