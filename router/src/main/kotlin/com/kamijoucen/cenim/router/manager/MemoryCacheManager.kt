package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.concurrent.ConcurrentHashMap

@Lazy
@Component("memoryCacheManager")
class MemoryCacheManager : ICacheManager {

    private val data = ConcurrentHashMap<String, Serializable>(16)

    override fun expire(key: String, time: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(key: String, value: Serializable): Boolean {
        data[key] = value
        return true
    }

    override fun get(key: String): String? {
        return data[key]?.toString()
    }

    override fun del(key: String): Boolean {
        data.remove(key)
        return true
    }


}