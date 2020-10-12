package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.io.Serializable

@Lazy
@Component("redisCacheManager")
class RedisCacheManager : ICacheManager {

    @Autowired
    private lateinit var redisTemplate: StringRedisTemplate

    override fun expire(key: String, time: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(key: String, value: Serializable): Boolean {
        redisTemplate.opsForValue().set(key, value.toString())
        return true
    }

    override fun get(key: String): String? {
        return redisTemplate.opsForValue().get(key)
    }

    override fun del(key: String): Boolean {
        return redisTemplate.delete(key)
    }


}