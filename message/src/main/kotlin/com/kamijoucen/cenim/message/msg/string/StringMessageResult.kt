package com.kamijoucen.cenim.message.msg.string

import com.kamijoucen.cenim.message.msg.MessageResult

class StringMessageResult : MessageResult() {

    var text = ""

    override fun getProperty(key: String): String = ""

    override fun getContent(): String = text

}