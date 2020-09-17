package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.util.AckSender
import com.kamijoucen.cenim.router.util.MsgSender
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientToRouterHandler : SimpleChannelInboundHandler<Message>() {

    private val log = LogFactory.getLog("ClientToRouterHandler")

    @Autowired
    lateinit var connContext: RouterContext

    @Autowired
    lateinit var msgSender: MsgSender

    @Autowired
    lateinit var ackSender: AckSender

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        // send ack msg
        ackSender.ack(msg, ctx)

        checkFrom(msg)
        checkDest(msg)

        // parse msg
        val process = connContext.routerMsgProcessManager.getRequestProcess(msg.header.bodyType)
        if (process == null) {
            msgSender.sendMsg(msg)
            return
        }
        val result = process.accept(msg, ctx)
        if (result.success && result.next) {
            msgSender.sendMsg(msg)
        }
    }

//    override fun channelActive(ctx: ChannelHandlerContext) {
//        connContext.clientToRouterConnManager.addConn(ClientToRouterConn(ctx))
//    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        try {
//            TODO("reconnect")
        } catch (ex: Exception) {
            connContext.routerToServiceServerConnManager.removeConn(ctx)
        }
    }

    fun checkFrom(msg: Message): Boolean {
        return false
    }

    fun checkDest(msg: Message): Boolean {
        return false
    }
}