package com.kamijoucen.cenim.message.msg.body

import com.kamijoucen.cenim.message.msg.MessageBody

typealias OlMsgBody = OnlineMessageBody

class OnlineMessageBody(private val userId: String) : MessageBody() {

    override fun execute(): OnlineMessageResult {
        return OnlineMessageResult(userId)
    }
}