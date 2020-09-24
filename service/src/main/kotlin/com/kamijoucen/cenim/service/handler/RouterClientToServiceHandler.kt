package com.kamijoucen.cenim.service.handler

import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import com.kamijoucen.cenim.service.domain.RouterClientToServiceConn
import com.kamijoucen.cenim.service.manager.ChatService
import com.kamijoucen.cenim.service.manager.ServiceContext
import com.kamijoucen.cenim.service.util.MsgSender
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class RouterClientToServiceHandler : SimpleChannelInboundHandler<Message>() {

    @Autowired
    private lateinit var serviceContext: ServiceContext

    @Autowired
    private lateinit var chatService: ChatService

    @Autowired
    private lateinit var msgSender: MsgSender

    override fun channelRead0(context: ChannelHandlerContext, msg: Message) {
        msgSender.ack(msg.header.msgId, context)
        chatService.doChat(msg)
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        serviceContext.routerClientToServiceConnManager.addConn(RouterClientToServiceConn(ctx))
    }
}