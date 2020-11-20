package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.message.msg.Message
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.stereotype.Component

@Component
@ChannelHandler.Sharable
class MsgPreProcessHandler : SimpleChannelInboundHandler<Message>() {

    override fun channelRead0(context: ChannelHandlerContext, msg: Message) {

        TODO("Not yet implemented")
    }
}