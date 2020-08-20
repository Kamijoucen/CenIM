package com.kamijoucen.cenim.message.msg.body

import com.kamijoucen.cenim.message.msg.MessageBody

class CustomMessageBody : MessageBody() {

    private val parameters = HashMap<String, String>()

    override fun execute(): CustomMessageResult {
        return CustomMessageResult(HashMap<String, String>(parameters.size)
                .also {
                    it.putAll(parameters)
                })
    }

    fun addParam(key: String, value: String) = parameters.put(key, value)

    fun getParam(key: String) = parameters[key]
}