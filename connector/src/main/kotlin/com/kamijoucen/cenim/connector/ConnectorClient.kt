package com.kamijoucen.cenim.connector

fun startConnectorClient(config: ConnectorConfig): Boolean {
    var urls = config.transfer

    for (url in urls) {
        println(url)
    }
    return false
}