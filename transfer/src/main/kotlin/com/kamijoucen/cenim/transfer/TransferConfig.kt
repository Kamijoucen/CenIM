package com.kamijoucen.cenim.transfer

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("app.transfer")
class TransferConfig {
    var port: Int = -1
    var business: ArrayList<String> = ArrayList()
}