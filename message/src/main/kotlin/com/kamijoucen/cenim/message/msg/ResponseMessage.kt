package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.common.util.JsonUtil
import io.netty.buffer.ByteBuf
import java.nio.charset.StandardCharsets

class ResponseMessage(header: MessageHeader, body: MessageBody)
    : Message(header, body) {

    companion object {}

    override val type: MessageType = MessageType.RESPONSE
}

fun ResponseMessage.Companion.decode(buf: ByteBuf): ResponseMessage {
    val version = buf.readInt()
    val type = buf.readInt()
    val fromId = buf.readLong()
    val toId = buf.readLong()
    val msgId = buf.readLong()

    val header = MessageHeader(version, type, fromId, toId, msgId)
    val body = JsonUtil.fromJson(buf.toString(StandardCharsets.UTF_8), ResponseMessage.getBodyClass(type))
    return ResponseMessage(header, body)
}


fun ResponseMessage.Companion.encode(buf: ByteBuf, msg: ResponseMessage) {
    buf.writeInt(msg.header.version)
    buf.writeInt(msg.header.type)
    buf.writeLong(msg.header.fromId)
    buf.writeLong(msg.header.toId)
    buf.writeLong(msg.header.msgId)
    buf.writeBytes(JsonUtil.toJson(msg.body).toByteArray())
}

fun ResponseMessage.Companion.getBodyClass(type: Int): Class<out MessageBody> {
    return ResponseBodyType.fromType(type).opClass
}