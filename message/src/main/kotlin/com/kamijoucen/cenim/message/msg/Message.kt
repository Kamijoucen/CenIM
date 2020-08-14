package com.kamijoucen.cenim.message.msg

abstract class Message(val header: MessageHeader, val body: MessageBody) {

    abstract val type: MessageType

    companion object {}

    enum class MessageType {
        REQUEST,
        RESPONSE
    }

}


