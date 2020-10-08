package com.kamijoucen.cenim.message.msg.body

import com.kamijoucen.cenim.message.msg.MessageBody

class ConnectMessageBody(private val userId: String) : MessageBody() {

    override fun execute(): ConnectMessageResult {
        return ConnectMessageResult(userId)
    }
}