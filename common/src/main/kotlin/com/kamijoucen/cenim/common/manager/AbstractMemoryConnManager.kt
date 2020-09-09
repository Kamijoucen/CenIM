package com.kamijoucen.cenim.common.manager

import com.kamijoucen.cenim.common.conn.IMConn
import io.netty.channel.ChannelHandlerContext
import java.io.Serializable
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

abstract class AbstractMemoryConnManager<C : IMConn> : ImConnManager<C> {

    private val connMap = ConcurrentHashMap<Serializable, C>()

    override fun getConn(): C? {
        return randomConn()
    }

    override fun getConn(ctx: ChannelHandlerContext): C? {
        TODO("Not yet implemented")
    }

    override fun getConn(netId: Serializable): C? {
        return connMap[netId]
    }

    override fun randomConn(): C? {
        return when (connMap.size) {
            0 -> null
            else -> connMap.toList()[Random.Default.nextInt(connMap.size)].second
        }
    }

    override fun addConn(conn: C): C {
        connMap[conn.getId()] = conn
        return conn
    }

    override fun removeConn(netId: Serializable): C? {
        return connMap.remove(netId)
    }

    override fun removeConn(ctx: ChannelHandlerContext): C? {
        TODO("Not yet implemented")
    }

    override fun removeAllConn() {
        connMap.clear()
    }
}