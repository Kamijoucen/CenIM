package com.kamijoucen.cenim.message.msg.body

import com.kamijoucen.cenim.common.util.JsonUtil
import com.kamijoucen.cenim.message.msg.MessageResult

class CustomMessageResult(private val parameters: HashMap<String, String>) : MessageResult() {

    override fun getProperty(key: String): String = parameters[key] ?: ""

    override fun getContent(): String = JsonUtil.toJson(parameters)

}