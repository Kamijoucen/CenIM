package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.service.UserChatService
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
    private lateinit var chatService: UserChatService

    @Autowired
    private lateinit var ackSender: AckSender

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        // send ack msg
        ackSender.ack(msg, ctx)
        checkFrom(msg)
        checkDest(msg)
        val result = connContext.routerMsgProcessManager.process[msg.header.bodyType]
                ?.accept(msg, ctx)?.let {
                    if (it.success) log.error("msg process error")
                }
        if (result == null) {
            chatService.senMsg(msg)
        }
    }

//    override fun channelActive(ctx: ChannelHandlerContext) {
//        connContext.clientToRouterConnManager.addConn(ClientToRouterConn(ctx))
//    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        connContext.clientToRouterConnManager.removeConn(ctx)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        log.error(cause.message, cause)
    }

    fun checkFrom(msg: Message): Boolean {
        return false
    }

    fun checkDest(msg: Message): Boolean {
        return false
    }
}