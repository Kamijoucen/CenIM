package com.kamijoucen.cenim.connector.handler

import com.kamijoucen.cenim.connector.conn.ConnectorToTransferConn
import com.kamijoucen.cenim.connector.manager.ConnectorContext
import com.kamijoucen.cenim.message.msg.RequestMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConnectorToTransferHandler : SimpleChannelInboundHandler<RequestMessage>() {

    @Autowired
    lateinit var connContext: ConnectorContext

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        super.channelInactive(ctx)
        TODO("reconnect")
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        super.channelActive(ctx)
        if (ctx != null) {
            connContext.transferConnContextManager.addConn(ConnectorToTransferConn(ctx))
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        if (ctx != null) {
            connContext.transferConnContextManager.removeConn(ctx)
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: RequestMessage?) {
        if (ctx == null || msg == null) {
            return
        }
        val consumer = connContext.clientMsgParseManager.getRequestParse(msg.header.type)
        consumer.accept(msg, ctx)
    }
}
