package com.kamijoucen.cenim.message.msg

import io.netty.buffer.ByteBuf

abstract class Message<BODY : MessageBody>(val header: MessageHeader, val body: MessageBody) {

    fun encode(buf: ByteBuf) {
        buf.writeInt(this.header.version)
        buf.writeBytes(this.header.msgId.toByteArray())
//        buf.writeBytes(this.header.type.toString().toByteArray())
        buf.writeInt(this.body.length)
        buf.writeBytes(this.body.toString().toByteArray())
        // TODO: 2020/8/7 临时实现
    }

    fun decode(buf: ByteBuf) {
        TODO()
    }

}