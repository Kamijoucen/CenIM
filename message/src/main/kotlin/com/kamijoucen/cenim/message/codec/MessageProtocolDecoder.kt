package com.kamijoucen.cenim.message.codec

import com.kamijoucen.cenim.message.msg.RequestMessage
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import org.apache.commons.lang3.ObjectUtils

class MessageProtocolDecoder : MessageToMessageDecoder<ByteBuf>() {

    override fun decode(context: ChannelHandlerContext?, byteBuf: ByteBuf?, out: MutableList<Any>?) {
        if (byteBuf == null || out == null) {
            return
        }
        out.add(RequestMessage().decode(byteBuf))
    }
}