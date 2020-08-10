package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.common.util.JsonUtil
import com.kamijoucen.cenim.message.msg.string.StringOperation
import io.netty.buffer.ByteBuf
import java.nio.charset.StandardCharsets

abstract class Message<BODY : MessageBody>(var header: MessageHeader, var body: BODY?) {


    abstract fun getMessageBodyOperationClass(type: Int): Class<out BODY>

    fun encode(buf: ByteBuf): Message<out BODY> {
        buf.writeInt(this.header.version)
        buf.writeInt(this.header.type)
        buf.writeLong(this.header.fromId)
        buf.writeLong(this.header.toId)
        buf.writeBytes(JsonUtil.toJson(this.body?:StringOperation()).toByteArray())
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