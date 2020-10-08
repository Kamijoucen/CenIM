package com.kamijoucen.cenim.client.handler

import com.kamijoucen.cenim.client.manager.AckWindowManager
import com.kamijoucen.cenim.client.manager.MsgProcessManager
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.MessageBodyType
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

@ChannelHandler.Sharable
class ResponseDispatcherHandler(private val ackManager: AckWindowManager,
                                private val processes: MsgProcessManager)
    : SimpleChannelInboundHandler<Message>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        if (msg.header.bodyType == MessageBodyType.ACK_MSG.type) {
            ackManager.set(msg.body.execute().getContent().toLong(), msg)
            return
        }
        val process = processes.getProcess(msg.header.bodyType)
        if (process != null) {
            process.process(msg)
        } else {
            println("-------------------------------  no process")
        }
    }

}