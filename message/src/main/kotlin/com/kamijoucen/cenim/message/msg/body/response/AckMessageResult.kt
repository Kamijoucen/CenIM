package com.kamijoucen.cenim.message.msg.body.response

import com.kamijoucen.cenim.message.msg.MessageResult

class AckMessageResult(private val srcId: Long) : MessageResult() {

    override fun getContent(): String = srcId.toString()

}