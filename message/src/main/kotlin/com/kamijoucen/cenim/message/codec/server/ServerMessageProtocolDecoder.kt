package com.kamijoucen.cenim.message.codec.server

import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.decode
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder

class ServerMessageProtocolDecoder : MessageToMessageDecoder<ByteBuf>() {

    override fun decode(context: ChannelHandlerContext?, byteBuf: ByteBuf?, out: MutableList<Any>?) {
        if (byteBuf == null || out == null) {
            return
        }
        out.add(RequestMessage.decode(byteBuf))
    }
}