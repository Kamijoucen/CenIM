package com.kamijoucen.cenim.service.handler

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.service.domain.RouterClientToServiceConn
import com.kamijoucen.cenim.service.manager.ChatService
import com.kamijoucen.cenim.service.manager.ServiceContext
import com.kamijoucen.cenim.service.util.MsgSender
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
@ChannelHandler.Sharable
class RouterClientToServiceHandler : SimpleChannelInboundHandler<Message>() {

    private val log = LogFactory.getLog("RouterClientToServiceHandler")

    @Autowired
    private lateinit var serviceContext: ServiceContext

    @Autowired
    private lateinit var chatService: ChatService

    @Autowired
    private lateinit var msgSender: MsgSender

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        msgSender.ack(msg.header.msgId, ctx)

        val process = serviceContext.msgProcessManager.getProcess(msg.header.bodyType)
        if (process != null) {
            val result = process.accept(msg, ctx)
            if (!result.success) {
                log.error("msg process error")
                return
            }
            if (result.next) {
                chatService.doChat(msg)
            }
        } else {
            chatService.doChat(msg)
        }
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        serviceContext.routerClientToServiceConnManager.addConn(RouterClientToServiceConn(ctx))
    }
}