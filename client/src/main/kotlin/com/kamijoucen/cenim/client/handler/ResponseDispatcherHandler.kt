package com.kamijoucen.cenim.client.handler

import com.kamijoucen.cenim.client.manager.AckWindowManager
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.MessageBodyType
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

@ChannelHandler.Sharable
class ResponseDispatcherHandler(private val ackManager: AckWindowManager)
    : SimpleChannelInboundHandler<Message>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        if (msg.header.bodyType == MessageBodyType.ACK_MSG.type) {
            ackManager.set(msg.header.msgId, msg)
            return
        }
    }

}