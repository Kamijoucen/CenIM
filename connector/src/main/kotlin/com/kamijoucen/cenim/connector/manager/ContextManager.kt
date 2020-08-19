package com.kamijoucen.cenim.connector.manager

import io.netty.channel.ChannelHandlerContext
import kotlin.random.Random

class ContextManager {

    private val ctxList = ArrayList<ChannelHandlerContext>()

    fun put(ctxList: ChannelHandlerContext) = this.ctxList.add(ctxList)

    fun getRandom() = when (ctxList.size) {
        0 -> throw RuntimeException()
        else -> ctxList[Random.Default.nextInt(ctxList.size)]
    }
}