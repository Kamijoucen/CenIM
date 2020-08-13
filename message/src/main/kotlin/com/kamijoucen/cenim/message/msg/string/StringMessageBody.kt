package com.kamijoucen.cenim.message.msg.string

import com.kamijoucen.cenim.message.msg.MessageBody
import com.kamijoucen.cenim.message.msg.MessageResult

class StringMessageBody(var content: String) : MessageBody() {

    override fun execute(): MessageResult {
        val result = StringMessageResult()
        result.text = this.content
        return result
    }
}