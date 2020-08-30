package com.kamijoucen.cenim.message.msg.body.response

import com.kamijoucen.cenim.message.msg.MessageBody

typealias AckMsgBody = AckMessageBody

class AckMessageBody(private val srcId: Long) : MessageBody() {

    override fun execute() = AckMessageResult(srcId)
}