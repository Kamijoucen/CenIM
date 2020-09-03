package com.kamijoucen.cenim.service.util

object MappingKeyGenerator {

    fun userHostMappingKey(userId: String): String {
        return "tran:user-host:${userId}"
    }

}