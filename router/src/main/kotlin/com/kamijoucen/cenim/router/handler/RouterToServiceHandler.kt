package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.domain.RouterToServiceServerConn
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
class RouterToServiceHandler : SimpleChannelInboundHandler<Message>() {

    private val log = LogFactory.getLog("RouterToServiceHandler")

    @Autowired
    private lateinit var ackSender: AckSender

    @Autowired
    private lateinit var chatService: UserChatService

    @Autowired
    lateinit var connContext: RouterContext

    override fun channelInactive(ctx: ChannelHandlerContext) {
        try {
//            TODO("reconnect")
        } catch (ex: Exception) {
            connContext.routerToServiceServerConnManager.removeConn(ctx)
        }
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        connContext.routerToServiceServerConnManager.addConn(RouterToServiceServerConn(ctx))
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        connContext.routerToServiceServerConnManager.removeConn(ctx)
    }

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        // TODO: 2020/10/5 ACK

        chatService.receiveMsg(msg)
    }
}
