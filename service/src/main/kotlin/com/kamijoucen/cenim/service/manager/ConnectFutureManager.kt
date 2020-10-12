package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.service.util.UserConnectFuture
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ConnectFutureManager {

    private val connectFutureManager = ConcurrentHashMap<Long, UserConnectFuture>()

    fun waitConnect(id: Long) = connectFutureManager[id]?.get()

    fun isConnect(id: Long): Boolean {
        val future = connectFutureManager[id]
        return if (future == null) {
            false
        } else {
            future.get()
        }
    }

    fun set(id: Long, future: UserConnectFuture) {
        connectFutureManager[id] = future
    }

    fun remove(id: Long) = connectFutureManager.remove(id)

}