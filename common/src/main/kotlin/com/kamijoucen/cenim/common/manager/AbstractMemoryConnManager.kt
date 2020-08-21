package com.kamijoucen.cenim.common.manager

import com.kamijoucen.cenim.common.domain.IMConn
import io.netty.channel.ChannelHandlerContext
import java.io.Serializable
import java.util.concurrent.ConcurrentHashMap

abstract class AbstractMemoryConnManager<C : IMConn> : ImConnManager<C> {

    private val connMap = ConcurrentHashMap<Serializable, C>()

    override fun getConn(ctx: ChannelHandlerContext): C? {
        TODO("Not yet implemented")
    }

    override fun getConn(netId: Serializable): C? {
        return connMap[netId]
    }

    override fun addConn(conn: C) {
        connMap[conn.getId()] = conn
    }

    override fun removeConn(netId: Serializable) {
        connMap.remove(netId)
    }

    override fun removeConn(ctx: ChannelHandlerContext) {
        TODO("Not yet implemented")
    }

    override fun removeAllConn() {
        connMap.clear()
    }
}