package com.kamijoucen.cenim.connector

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Component
import java.io.Serializable


@Component
@Configuration
@ConfigurationProperties("app.connector")
class ConnectorConfig {
    var port: Int = -1
    var transfer: ArrayList<String> = ArrayList()
}