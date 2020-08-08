package com.kamijoucen.cenim.message.msg.string

import com.kamijoucen.cenim.message.msg.Operation
import com.kamijoucen.cenim.message.msg.OperationResult

class StringOperation : Operation() {

    var content: String? = null

    override fun execute(): OperationResult {
        val result = StringOperationResult()
        result.content = "这是测试文本"
        return result
    }
}