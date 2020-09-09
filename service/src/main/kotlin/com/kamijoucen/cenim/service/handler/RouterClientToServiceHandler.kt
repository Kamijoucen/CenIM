package com.kamijoucen.cenim.service.handler

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import com.kamijoucen.cenim.service.domain.RouterClientToServiceConn
import com.kamijoucen.cenim.service.manager.ServiceContext
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.stereotype.Component


@Component
class RouterClientToServiceHandler : SimpleChannelInboundHandler<Message>() {

    private lateinit var serviceContext: ServiceContext

    override fun channelRead0(context: ChannelHandlerContext, msg: Message) {

        val result = msg.body.execute()
        println("---------------------MSG---------------------")
        println(result.getContent())
        var responseMessage = Message(msg.header, StringMessageBody("这是响应文本"))
        context.writeAndFlush(responseMessage)
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        serviceContext.routerClientToServiceConnManager.addConn(RouterClientToServiceConn(ctx))
    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        super.channelInactive(ctx)
//        TODO("reconnect...")
    }
}