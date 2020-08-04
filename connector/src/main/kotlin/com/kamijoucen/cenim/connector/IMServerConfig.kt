package com.kamijoucen.cenim.connector

data class IMServerConfig(
        val port: Int
)

fun parseConfig(): IMServerConfig {
    val config = IMServerConfig(8080)
    return config
}