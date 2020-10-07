package com.kamijoucen.cenim.message.msg.util

import com.kamijoucen.cenim.common.util.IdGenerator
import com.kamijoucen.cenim.message.msg.*

class MessageFactory(private val fromId: Long) {

    fun getMsg(destId: Long, body: MessageBody): Message {
        val head = MessageHeader(MsgVersion.V1.version, MessageBodyType.fromBody(body).type,
                fromId, destId, IdGenerator.nextId())
        val message = Message(head, body)
        return message
    }


}