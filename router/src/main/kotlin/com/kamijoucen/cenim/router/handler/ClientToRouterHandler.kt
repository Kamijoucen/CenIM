package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.util.AckSender
import com.kamijoucen.cenim.router.util.MsgSender
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.router.domain.ClientToRouterConn
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientToRouterHandler : SimpleChannelInboundHandler<RequestMessage>() {

    private val log = LogFactory.getLog("ClientToRouterHandler")

    @Autowired
    lateinit var connContext: RouterContext

    @Autowired
    lateinit var msgSender: MsgSender

    @Autowired
    lateinit var ackSender: AckSender

    override fun channelRead0(ctx: ChannelHandlerContext, msg: RequestMessage) {
        // send ack msg
        ackSender.ack(msg, ctx)
        // parse msg
        val process = connContext.routerMsgProcessManager.getRequestParse(msg.header.type)
        if (process != null) {
            process.accept(msg, ctx).let {
                if (it.success && it.next) msgSender.sendMsg(msg)
            }
        } else {
            msgSender.sendMsg(msg)
        }
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        connContext.clientToRouterConnManager.addConn(ClientToRouterConn(ctx))
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        try {
//            TODO("reconnect")
        } catch (ex: Exception) {
            connContext.routerToServiceServerConnManager.removeConn(ctx)
        }
    }
}