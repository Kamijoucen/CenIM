package com.kamijoucen.cenim.message.msg.string

import com.kamijoucen.cenim.message.msg.MessageBody
import com.kamijoucen.cenim.message.msg.MessageResult

class StringMessageBody : MessageBody() {

    var content: String? = ""

    override fun execute(): MessageResult {
        val result = StringMessageResult()
        result.content = this.content ?: ""
        return result
    }
}