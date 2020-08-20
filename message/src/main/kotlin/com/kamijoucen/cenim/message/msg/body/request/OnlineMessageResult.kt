package com.kamijoucen.cenim.message.msg.body.request

import com.kamijoucen.cenim.message.msg.MessageResult

class OnlineMessageResult : MessageResult() {

    var text = ""

    override fun getProperty(key: String): String = ""

    override fun getContent(): String = text

}