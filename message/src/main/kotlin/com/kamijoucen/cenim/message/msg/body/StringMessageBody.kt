package com.kamijoucen.cenim.message.msg.body

import com.kamijoucen.cenim.message.msg.MessageBody

typealias StrMsgBody = StringMessageBody

class StringMessageBody(var content: String) : MessageBody() {

    override fun execute(): StringMessageResult {
        val result = StringMessageResult()
        result.text = this.content
        return result
    }
}