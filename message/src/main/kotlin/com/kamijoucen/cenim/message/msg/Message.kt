package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.common.util.JsonUtil
import io.netty.buffer.ByteBuf
import java.nio.charset.StandardCharsets

abstract class Message<BODY : MessageBody> {

    lateinit var header: MessageHeader
    lateinit var body: MessageBody


    abstract fun getMessageBodyOperationClass(type: Int): Class<out BODY>

    fun encode(buf: ByteBuf): Message<out BODY> {
        buf.writeInt(this.header.version)
        buf.writeInt(this.header.type)
        buf.writeLong(this.header.fromId)
        buf.writeLong(this.header.toId)
        buf.writeBytes(this.body.toString().toByteArray())
        return this
    }

    fun decode(buf: ByteBuf): Message<out BODY> {
        this.header.version = buf.readInt()
        this.header.type = buf.readInt()
        this.header.fromId = buf.readLong()
        this.header.toId = buf.readLong()
        val bodyClazz = getMessageBodyOperationClass(this.header.type)
        this.body = JsonUtil.fromJson(buf.toString(StandardCharsets.UTF_8), bodyClazz)
        return this
    }

}