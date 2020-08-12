package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.common.util.JsonUtil
import io.netty.buffer.ByteBuf
import java.nio.charset.StandardCharsets

class RequestMessage(header: MessageHeader, body: MessageBody)
    : Message(header, body) {

    companion object {}

    override val type: MessageType = MessageType.REQUEST
}

fun RequestMessage.Companion.decode(buf: ByteBuf): RequestMessage {
    val version = buf.readInt()
    val type = buf.readInt()
    val fromId = buf.readLong()
    val toId = buf.readLong()
    val msgId = buf.readLong()

    val header = MessageHeader(version, type, fromId, toId, msgId)
    val body = JsonUtil.fromJson(buf.toString(StandardCharsets.UTF_8), RequestMessage.getBodyClass(type))
    return RequestMessage(header, body)
}


fun RequestMessage.Companion.encode(buf: ByteBuf, msg: RequestMessage) {
    buf.writeInt(msg.header.version)
    buf.writeInt(msg.header.type)
    buf.writeLong(msg.header.fromId)
    buf.writeLong(msg.header.toId)
    buf.writeLong(msg.header.msgId)
    buf.writeBytes(JsonUtil.toJson(msg.body).toByteArray())
}

fun RequestMessage.Companion.getBodyClass(type: Int): Class<out MessageBody> {
    return RequestBodyType.fromType(type).opClass
}