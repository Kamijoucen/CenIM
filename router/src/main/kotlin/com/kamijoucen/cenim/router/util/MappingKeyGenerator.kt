package com.kamijoucen.cenim.router.util

object MappingKeyGenerator {

    fun userHostKey(userId: String): String {
        return "router:user-host:${userId}"
    }

}