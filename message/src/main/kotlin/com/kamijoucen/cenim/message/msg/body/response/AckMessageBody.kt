package com.kamijoucen.cenim.message.msg.body.response

import com.kamijoucen.cenim.message.msg.MessageBody

typealias AckMsgBody = AckMessageBody

class AckMessageBody(var content: String) : MessageBody() {

    override fun execute(): AckMessageResult {
        val result = AckMessageResult()
        result.text = this.content
        return result
    }
}