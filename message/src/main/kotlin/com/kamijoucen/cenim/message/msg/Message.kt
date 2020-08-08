package com.kamijoucen.cenim.message.msg

import io.netty.buffer.ByteBuf

abstract class Message<BODY : MessageBody> {

    lateinit var header: MessageHeader
    lateinit var body: MessageBody

    abstract fun encode(buf: ByteBuf)

    abstract fun decode(buf: ByteBuf)

    fun defaultEncode(buf: ByteBuf) {
        buf.writeInt(this.header.version)
        buf.writeLong(this.header.fromId)
        buf.writeLong(this.header.toId)
        buf.writeBytes(this.body.toString().toByteArray())
    }

    fun defaultDecode(buf: ByteBuf) {
        this.header.version = buf.readInt()
        this.header.fromId = buf.readLong()
        this.header.toId = buf.readLong()
    }

}