package com.kamijoucen.cenim.common.conn

import com.kamijoucen.cenim.common.util.IdGenerator
import io.netty.channel.ChannelHandlerContext
import java.io.Serializable

abstract class AbstractIMConn(private val context: ChannelHandlerContext) : IMConn {

    private val netId: Serializable

    init {
        this.netId = IdGenerator.nextId().toString()
        this.context.channel().attr(IMConn.NET_ID).set(netId)
    }

    override fun getId() = netId

    override fun getCtx() = context

    override fun close() {
        context.close()
    }
}