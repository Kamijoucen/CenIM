package com.kamijoucen.cenim.common.conn

import io.netty.channel.ChannelHandlerContext
import io.netty.util.AttributeKey
import java.io.Serializable

interface IMConn {

    companion object {
        val NET_ID = AttributeKey.valueOf<Serializable>("netId")
    }

    fun getId(): Serializable

    fun getCtx(): ChannelHandlerContext

    fun close()

}
