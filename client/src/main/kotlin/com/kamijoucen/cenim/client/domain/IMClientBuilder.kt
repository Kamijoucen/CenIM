package com.kamijoucen.cenim.client.domain

import com.kamijoucen.cenim.client.IMClient
import com.kamijoucen.cenim.client.handler.ResponseDispatcherHandler
import com.kamijoucen.cenim.client.manager.AckWindowManager

class IMClientBuilder(private val hosts: List<IMHost>) {

    fun build(userId: Long, pwd: String): IMClient {
        val host = hosts.random()
        val ackWindowManager = AckWindowManager()
        val channelFuture = startClient(host, ResponseDispatcherHandler(ackWindowManager))
        return IMClient(userId, host, channelFuture, ackWindowManager)
    }

    private fun sendLogin(x: Any): Unit {

    }

}