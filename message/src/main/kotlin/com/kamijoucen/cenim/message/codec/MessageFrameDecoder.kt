package com.kamijoucen.cenim.message.codec

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.LengthFieldBasedFrameDecoder

class MessageFrameDecoder
    : LengthFieldBasedFrameDecoder(
        10240, 0, 2, 0, 2) {

    override fun decode(ctx: ChannelHandlerContext?, inBuf: ByteBuf?): Any {
        // TODO: 2020/8/6
        return super.decode(ctx, inBuf)
    }
}

