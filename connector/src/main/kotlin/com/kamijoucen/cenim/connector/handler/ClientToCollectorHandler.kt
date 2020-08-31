package com.kamijoucen.cenim.connector.handler

import com.kamijoucen.cenim.connector.manager.ConnectorContext
import com.kamijoucen.cenim.connector.service.AckSender
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

    @Autowired
    lateinit var ackSender: AckSender

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: RequestMessage?) {
        if (ctx == null || msg == null) {
            return
        }
        // send ack msg
        ackSender.ack(msg, ctx)
        // parse msg
        connContext.connectorMsgParseManager.getRequestParse(msg.header.type).accept(msg, ctx)
        // msg to transfer
        msgSender.sendMsg(msg)
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