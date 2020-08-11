package com.kamijoucen.cenim.message.codec

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.encode
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

class MessageProtocolEncoder : MessageToMessageEncoder<RequestMessage>() {

    override fun encode(context: ChannelHandlerContext?, msg: RequestMessage?, out: MutableList<Any>?) {
        if (context == null || msg == null || out == null) {
            return
        }
        val buffer = context.alloc().buffer()
        RequestMessage.encode(buffer, msg)
        out.add(buffer)
    }

}