package com.kamijoucen.cenim.router.util

object MappingKeyGenerator {

    fun userToServiceKey(userId: String): String {
        return "router:user-host:${userId}"
    }

    fun userToRouterKey(userId: String): String {
        return "client:user-host:${userId}"
    }

}