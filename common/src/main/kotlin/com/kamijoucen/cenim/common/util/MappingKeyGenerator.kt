package com.kamijoucen.cenim.common.util

object MappingKeyGenerator {

    fun routerToService(userId: String): String {
        return "conn:router:service:${userId}"
    }

    fun clientToUser(userId: String): String {
        return "conn:client:router:${userId}"
    }

}