package com.kamijoucen.cenim.message.codec.protocol

import com.kamijoucen.cenim.common.util.JsonUtil
import com.kamijoucen.cenim.message.msg.Message
import io.netty.buffer.ByteBuf

object MessageEncoder {

    // TypeMessage -> head -> content
    fun encode(msg: Message, buf: ByteBuf) {
        buf.writeInt(msg.header.version)
        buf.writeInt(msg.header.bodyType)
        buf.writeLong(msg.header.fromId)
        buf.writeLong(msg.header.destId)
        buf.writeLong(msg.header.msgId)
        buf.writeBytes(JsonUtil.toJson(msg.body).toByteArray())
    }

}