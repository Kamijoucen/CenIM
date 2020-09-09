package com.kamijoucen.cenim.message.codec

import com.kamijoucen.cenim.message.codec.protocol.MessageDecoder
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder

class MessageProtocolDecoder : MessageToMessageDecoder<ByteBuf>() {

    override fun decode(context: ChannelHandlerContext, byteBuf: ByteBuf, out: MutableList<Any>) {
        out.add(MessageDecoder.decode(byteBuf))
    }
}