package com.kamijoucen.cenim.connector.domain

import com.kamijoucen.cenim.common.domain.IMConn
import com.kamijoucen.cenim.connector.manager.ChCtx
import io.netty.channel.ChannelHandlerContext
import java.io.Serializable

class ClientToConnectorConn(ctx: ChCtx) : IMConn {

    override fun getId(): Serializable {
        TODO("Not yet implemented")
    }

    override fun getCtx(): ChannelHandlerContext {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}