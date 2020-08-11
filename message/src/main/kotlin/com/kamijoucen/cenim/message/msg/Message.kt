package com.kamijoucen.cenim.message.msg

abstract class Message(val header: MessageHeader, val body: MessageBody) {

    companion object {}

    enum class MessageType {
        REQUEST,
        RESPONSE
    }

    abstract val type: MessageType
}


