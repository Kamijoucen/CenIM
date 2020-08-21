package com.kamijoucen.cenim.common.manager

import com.kamijoucen.cenim.common.domain.IMConn
import io.netty.channel.ChannelHandlerContext
import java.io.Serializable

abstract class AbstractMemoryConnManager<C : IMConn> : ImConnManager<C> {
    override fun getConn(ctx: ChannelHandlerContext?): C {
        TODO("Not yet implemented")
    }

    override fun getConn(netId: Serializable?): C {
        TODO("Not yet implemented")
    }

    override fun addConn(conn: C) {
        TODO("Not yet implemented")
    }

    override fun removeConn(netId: Serializable?) {
        TODO("Not yet implemented")
    }

    override fun removeConn(ctx: ChannelHandlerContext?) {
        TODO("Not yet implemented")
    }

    override fun removeAllConn() {
        TODO("Not yet implemented")
    }
}