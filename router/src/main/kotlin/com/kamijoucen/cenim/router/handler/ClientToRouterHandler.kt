package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.util.AckSender
import com.kamijoucen.cenim.router.util.MsgSender
import com.kamijoucen.cenim.message.msg.RequestMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientToRouterHandler : SimpleChannelInboundHandler<RequestMessage>() {

    @Autowired
    lateinit var connContext: RouterContext

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
        val result = connContext.routerMsgParseManager.getRequestParse(msg.header.type)
                ?.accept(msg, ctx)
        // send msg to service
        if (result == null || result.success) {
            msgSender.sendMsg(msg)
        }
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