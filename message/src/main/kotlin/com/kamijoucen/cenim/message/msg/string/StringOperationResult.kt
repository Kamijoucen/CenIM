package com.kamijoucen.cenim.message.msg.string

import com.kamijoucen.cenim.message.msg.OperationResult

class StringOperationResult : OperationResult() {
    var content: String = ""

    override fun getProperty(key: String): String {
        if (key == "content") {
            return content
        }
        return ""
    }
}