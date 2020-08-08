package com.kamijoucen.cenim.connector

data class ConnectorConfig(
        val port: Int
)

fun parseConfig(): ConnectorConfig {
    val config = ConnectorConfig(8080)
    return config
}