package com.kamijoucen.cenim.common.manager

import com.kamijoucen.cenim.common.conn.IMConn
import io.netty.channel.ChannelHandlerContext
import java.io.Serializable

interface ImConnManager<C : IMConn> {

    fun getConn(): C?

    fun getConn(ctx: ChannelHandlerContext): C?

    fun getConn(netId: Serializable): C?

    fun randomConn(): C?

    fun addConn(conn: C): C

    fun removeConn(netId: Serializable): C?

    fun removeConn(ctx: ChannelHandlerContext): C?

    fun removeAllConn()

}