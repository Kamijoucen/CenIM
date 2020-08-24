package com.kamijoucen.cenim.connector.handler

import com.kamijoucen.cenim.connector.manager.ConnectorContext
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.ResponseMessage
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientToCollectorHandler : SimpleChannelInboundHandler<RequestMessage>() {

    @Autowired
    lateinit var connContext: ConnectorContext

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: RequestMessage?) {
        if (ctx == null || msg == null) {
            return
        }
        val result = msg.body.execute()

        val consumer = connContext.clientMsgParseManager.getRequestParse(msg.header.type)
        consumer.accept(msg, ctx)
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        super.channelActive(ctx)
//        TODO("LOG...")
    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        super.channelInactive(ctx)
//        TODO("reconnect...")
    }
}