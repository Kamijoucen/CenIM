package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Lazy
@Component("memoryCacheManager")
class MemoryCacheManager : ICacheManager {

    private val data = ConcurrentHashMap<String, String>(16)

    override fun expire(key: String, time: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(key: String, value: String): Boolean {
        data[key] = value
        return true
    }

    override fun get(key: String): String? {
        return data[key]
    }

    override fun del(key: String): Boolean {
        data.remove(key)
        return true
    }


}