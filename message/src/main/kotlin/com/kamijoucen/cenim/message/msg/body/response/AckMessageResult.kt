package com.kamijoucen.cenim.message.msg.body.response

import com.kamijoucen.cenim.message.msg.MessageResult

class AckMessageResult(val srcId: String) : MessageResult() {

    override fun getProperty(key: String): String = ""

    override fun getContent(): String = srcId

}