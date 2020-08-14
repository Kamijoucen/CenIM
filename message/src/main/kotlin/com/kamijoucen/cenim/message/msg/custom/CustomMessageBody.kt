package com.kamijoucen.cenim.message.msg.custom

import com.kamijoucen.cenim.message.msg.MessageBody
import com.kamijoucen.cenim.message.msg.MessageResult

class CustomMessageBody : MessageBody() {

    private val parameters = HashMap<String, String>()

    override fun execute(): MessageResult {
        return CustomMessageResult(HashMap<String, String>(parameters.size)
                .also {
                    it.putAll(parameters)
                })
    }

    fun addParam(key: String, value: String) = parameters.put(key, value)

    fun getParam(key: String) = parameters[key]
}