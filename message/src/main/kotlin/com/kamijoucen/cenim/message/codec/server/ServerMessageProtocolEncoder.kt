package com.kamijoucen.cenim.message.codec.server

import com.kamijoucen.cenim.message.msg.ResponseMessage
import com.kamijoucen.cenim.message.msg.encode
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

class ServerMessageProtocolEncoder : MessageToMessageEncoder<ResponseMessage>() {

    override fun encode(context: ChannelHandlerContext?, msg: ResponseMessage?, out: MutableList<Any>?) {
        if (context == null || msg == null || out == null) {
            return
        }
        val buffer = context.alloc().buffer()
        ResponseMessage.encode(buffer, msg)
        out.add(buffer)
    }

}