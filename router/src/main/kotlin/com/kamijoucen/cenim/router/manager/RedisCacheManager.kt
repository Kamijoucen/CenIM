package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.io.Serializable

@Lazy
@Component("redisCacheManager")
class RedisCacheManager : ICacheManager {

    override fun expire(key: String, time: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(key: String, value: Serializable): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(key: String): String? {
        TODO("Not yet implemented")
    }

    override fun del(key: String): Boolean {
        TODO("Not yet implemented")
    }


}