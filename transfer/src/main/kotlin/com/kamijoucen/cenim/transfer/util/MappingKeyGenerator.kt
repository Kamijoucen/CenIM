package com.kamijoucen.cenim.transfer.util

object MappingKeyGenerator {

    fun userHostMappingKey(userId: String): String {
        return "tran:user-host:${userId}"
    }

}