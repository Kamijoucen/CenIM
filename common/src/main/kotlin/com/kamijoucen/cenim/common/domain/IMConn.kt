package com.kamijoucen.cenim.common.domain

import io.netty.channel.ChannelHandlerContext
import java.io.Serializable

interface IMConn {

    fun getId(): Serializable

    fun getCtx(): ChannelHandlerContext

    fun close()

}