package com.kamijoucen.cenim.message.msg.string

import com.kamijoucen.cenim.message.msg.MessageResult

class StringMessageResult : MessageResult() {

    var content = ""

    override fun getProperty(key: String): String {
        return ""
    }

    override fun toString(): String {
        return content
    }
}