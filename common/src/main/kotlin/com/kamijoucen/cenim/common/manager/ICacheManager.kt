package com.kamijoucen.cenim.common.manager

import java.io.Serializable

interface ICacheManager {

    fun expire(key: String, time: Long): Boolean

    fun set(key: String, value: Serializable): Boolean

    fun get(key: String): String?

    fun del(key: String): Boolean
}