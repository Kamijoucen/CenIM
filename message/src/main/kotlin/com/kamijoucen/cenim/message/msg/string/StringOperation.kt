package com.kamijoucen.cenim.message.msg.string

import com.kamijoucen.cenim.message.msg.Operation
import com.kamijoucen.cenim.message.msg.OperationResult

class StringOperation : Operation() {

    var content: String? = ""

    override fun execute(): OperationResult {
        val result = StringOperationResult()
        result.content = this.content ?: ""
        return result
    }
}