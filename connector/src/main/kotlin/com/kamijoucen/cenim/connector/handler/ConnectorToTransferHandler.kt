package com.kamijoucen.cenim.connector.handler

import com.kamijoucen.cenim.connector.domain.ConnectorToTransferServerConn
import com.kamijoucen.cenim.connector.manager.ConnectorContext
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.ResponseMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConnectorToTransferHandler : SimpleChannelInboundHandler<ResponseMessage>() {

    @Autowired
    lateinit var connContext: ConnectorContext

    override fun channelInactive(ctx: ChannelHandlerContext?) {
//        super.channelInactive(ctx)
        if (ctx == null) {
            return
        }
        try {
//            TODO("reconnect")
        } catch (ex: Exception) {
            connContext.connectorToTransferServerConnManager.removeConn(ctx)
        }
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        if (ctx != null) {
            connContext.connectorToTransferServerConnManager.addConn(ConnectorToTransferServerConn(ctx))
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        if (ctx != null) {
            connContext.connectorToTransferServerConnManager.removeConn(ctx)
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ResponseMessage?) {
        println("aaaagg")
        if (ctx == null || msg == null) {
            return
        }
        println("111111111111111111111-----------222222222222")
    }
}
