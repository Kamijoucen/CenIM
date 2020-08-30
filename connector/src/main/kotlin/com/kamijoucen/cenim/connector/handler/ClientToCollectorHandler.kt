package com.kamijoucen.cenim.connector.handler

import com.kamijoucen.cenim.connector.manager.ConnectorContext
import com.kamijoucen.cenim.connector.service.MsgSender
import com.kamijoucen.cenim.message.msg.RequestMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientToCollectorHandler : SimpleChannelInboundHandler<RequestMessage>() {

    @Autowired
    lateinit var connContext: ConnectorContext

    @Autowired
    lateinit var msgSender: MsgSender

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: RequestMessage?) {
        if (ctx == null || msg == null) {
            return
        }
        connContext.clientMsgParseManager.getRequestParse(msg.header.type).accept(msg, ctx)
        msgSender.sendMsg(msg, ctx)
    }

//    override fun channelActive(ctx: ChannelHandlerContext?) {
//        super.channelActive(ctx)
////        TODO("LOG...")
//    }
//
//    override fun channelInactive(ctx: ChannelHandlerContext?) {
//        super.channelInactive(ctx)
////        TODO("reconnect...")
//    }
}