package com.kamijoucen.cenim.common.manager

interface ICacheManager {

    fun expire(key: String, time: Long): Boolean

    fun set(key: String, value: String): Boolean

    fun get(key: String): String?

    fun del(key: String): Boolean
}