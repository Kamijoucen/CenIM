package com.kamijoucen.cenim.message.codec

import com.kamijoucen.cenim.message.codec.protocol.MessageEncoder
import com.kamijoucen.cenim.message.msg.Message
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

class MessageProtocolEncoder : MessageToMessageEncoder<Message>() {

    override fun encode(context: ChannelHandlerContext, msg: Message, out: MutableList<Any>) {
        val buffer = context.alloc().buffer()
        MessageEncoder.encode(msg, buffer)
        out.add(buffer)
    }

}