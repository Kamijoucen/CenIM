package com.kamijoucen.cenim.common.util

import com.google.gson.Gson

object JsonUtil {
    private val GSON = Gson()

    fun <T> fromJson(jsonStr: String, clazz: Class<T>): T {
        return GSON.fromJson(jsonStr, clazz)
    }

    fun toJson(obj: Any): String {
        return GSON.toJson(obj)
    }
}