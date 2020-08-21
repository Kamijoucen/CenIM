package com.kamijoucen.cenim.common.manager

import com.kamijoucen.cenim.common.domain.IMConn
import io.netty.channel.ChannelHandlerContext
import java.io.Serializable

interface ImConnManager<C : IMConn> {

    fun getConn(ctx: ChannelHandlerContext): C?

    fun getConn(netId: Serializable): C?

    fun addConn(conn: C)

    fun removeConn(netId: Serializable)

    fun removeConn(ctx: ChannelHandlerContext)

    fun removeAllConn()

}