package com.kamijoucen.cenim.common.util

object MappingKeyGenerator {

    fun userToServiceKey(userId: String): String {
        return "router:user-host:${userId}"
    }

    fun userToRouterKey(userId: String): String {
        return "client:user-host:${userId}"
    }

}