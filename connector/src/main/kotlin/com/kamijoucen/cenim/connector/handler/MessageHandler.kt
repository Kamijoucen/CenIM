package com.kamijoucen.cenim.connector.handler

import com.kamijoucen.cenim.message.msg.RequestMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.stereotype.Component

@Component
class MessageHandler : SimpleChannelInboundHandler<RequestMessage>() {

    override fun channelRead0(context: ChannelHandlerContext?, msg: RequestMessage?) {
        if (context == null || msg == null) {
            return
        }


        TODO("Not yet implemented")
    }

}