package com.kamijoucen.cenim.message.msg.body.request

import com.kamijoucen.cenim.message.msg.MessageResult

class OnlineMessageResult(private val userId: String) : MessageResult() {


    override fun getProperty(key: String): String = ""

    override fun getContent(): String = userId

}