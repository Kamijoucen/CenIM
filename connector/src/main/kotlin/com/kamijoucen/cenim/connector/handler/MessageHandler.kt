package com.kamijoucen.cenim.connector.handler

import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.ResponseMessage
import com.kamijoucen.cenim.message.msg.string.StringMessageBody
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.stereotype.Component

@Component
class MessageHandler : SimpleChannelInboundHandler<RequestMessage>() {

    override fun channelRead0(context: ChannelHandlerContext?, msg: RequestMessage?) {
        if (context == null || msg == null) {
            return
        }
        val result = msg.body.execute()
        println("---------------------MSG---------------------")
        println(result.getContent())
        var responseMessage = ResponseMessage(msg.header, StringMessageBody("这是响应文本"))
        context.writeAndFlush(responseMessage)
    }

}