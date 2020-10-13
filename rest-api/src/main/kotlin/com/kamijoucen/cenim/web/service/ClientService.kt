package com.kamijoucen.cenim.web.service

import com.kamijoucen.cenim.client.IMClient
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class ClientService {

    private val map = ConcurrentHashMap<Long, IMClient>()

    fun set(id: Long, client: IMClient) {
        map[id] = client
    }

    fun get(id: Long): IMClient? {
        return map[id]
    }

    fun remove(id: Long): IMClient? {
        TODO()
    }

    fun close(id: Long): IMClient? {
        TODO()
    }

}