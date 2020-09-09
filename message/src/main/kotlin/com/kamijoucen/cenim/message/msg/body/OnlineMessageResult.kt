package com.kamijoucen.cenim.message.msg.body

import com.kamijoucen.cenim.message.msg.MessageResult

class OnlineMessageResult(private val userId: String) : MessageResult() {

    override fun getContent(): String = userId

}