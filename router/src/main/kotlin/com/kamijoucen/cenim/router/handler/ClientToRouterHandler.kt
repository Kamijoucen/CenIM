package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.domain.ClientToRouterConn
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.service.MsgService
import com.kamijoucen.cenim.router.util.AckSender
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@ChannelHandler.Sharable
class ClientToRouterHandler : SimpleChannelInboundHandler<Message>() {

    private val log = LogFactory.getLog("ClientToRouterHandler")

    @Autowired
    private lateinit var connContext: RouterContext

    @Autowired
    private lateinit var msgService: MsgService

    @Autowired
    private lateinit var ackSender: AckSender

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        // send ack msg
//        ackSender.ack(msg, ctx)
        checkFrom(msg)
        checkDest(msg)
        // parse msg
        val process = connContext.routerMsgProcessManager.getProcess(msg.header.bodyType)
        if (process != null) {
            val result = process.accept(msg, ctx)
            if (!result.success) {
                log.error("msg process error")
                return
            }
            if (result.next) {
                msgService.sendMsg(msg)
            }
        } else {
            msgService.sendMsg(msg)
        }
    }

//    override fun channelActive(ctx: ChannelHandlerContext) {
//        connContext.clientToRouterConnManager.addConn(ClientToRouterConn(ctx))
//    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        connContext.clientToRouterConnManager.removeConn(ctx)
    }

    fun checkFrom(msg: Message): Boolean {
        return false
    }

    fun checkDest(msg: Message): Boolean {
        return false
    }
}