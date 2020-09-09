package com.kamijoucen.cenim.message.codec.protocol

import com.kamijoucen.cenim.common.util.JsonUtil
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.MessageBodyType
import com.kamijoucen.cenim.message.msg.MessageHeader
import com.kamijoucen.cenim.message.msg.fromType
import io.netty.buffer.ByteBuf
import java.nio.charset.StandardCharsets

object MessageDecoder {

    fun decode(buf: ByteBuf): Message {

        val version = buf.readInt()
        val type = buf.readInt()
        val fromId = buf.readLong()
        val destId = buf.readLong()
        val msgId = buf.readLong()

        val header = MessageHeader(version, type, fromId, destId, msgId)
        val body = JsonUtil.fromJson(buf.toString(StandardCharsets.UTF_8), MessageBodyType.fromType(type).bodyClass)
        return Message(header, body)

    }

}