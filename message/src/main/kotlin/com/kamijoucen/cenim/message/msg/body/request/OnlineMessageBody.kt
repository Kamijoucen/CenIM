package com.kamijoucen.cenim.message.msg.body.request

import com.kamijoucen.cenim.message.msg.MessageBody

typealias OlMsgBody = OnlineMessageBody

class OnlineMessageBody : MessageBody() {

    // TODO: 2020/8/20 username password

    override fun execute(): OnlineMessageResult {
        return OnlineMessageResult()
    }
}