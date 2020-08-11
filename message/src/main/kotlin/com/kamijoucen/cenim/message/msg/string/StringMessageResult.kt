package com.kamijoucen.cenim.message.msg.string

import com.kamijoucen.cenim.message.msg.MessageResult

class StringMessageResult : MessageResult() {
    var content: String = ""

    override fun getProperty(key: String): String {
        if (key == "content") {
            return content
        }
        return ""
    }
}