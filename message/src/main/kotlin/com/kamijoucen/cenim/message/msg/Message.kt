package com.kamijoucen.cenim.message.msg

import io.netty.buffer.ByteBuf

abstract class Message<BODY : MessageBody>(
        val header: MessageHeader,
        val body: MessageBody
)

//
//fun <BODY : MessageBody> decode(buf: ByteBuf): Message<BODY> {
//
//}


fun <BODY : MessageBody> encode(buf: ByteBuf, message: Message<BODY>) {
    buf.writeInt(message.header.version)
    buf.writeBytes(message.header.msgId.toByteArray())
    buf.writeBytes(message.header.type.toString().toByteArray())
    buf.writeInt(message.body.length)
    buf.writeBytes(message.body.toString().toByteArray())
    // TODO: 2020/8/7 临时实现 
}