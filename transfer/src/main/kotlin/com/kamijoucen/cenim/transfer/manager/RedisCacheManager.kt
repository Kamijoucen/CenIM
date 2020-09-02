package com.kamijoucen.cenim.transfer.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Lazy
@Component("redisCacheManager")
class RedisCacheManager : ICacheManager {

    override fun expire(key: String, time: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(key: String, value: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(key: String): String? {
        TODO("Not yet implemented")
    }

    override fun del(key: String): Boolean {
        TODO("Not yet implemented")
    }


}