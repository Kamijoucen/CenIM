package com.kamijoucen.cenim.message.msg.body.response

import com.kamijoucen.cenim.message.msg.MessageResult

class AckMessageResult : MessageResult() {

    var text = ""

    override fun getProperty(key: String): String = ""

    override fun getContent(): String = text

}