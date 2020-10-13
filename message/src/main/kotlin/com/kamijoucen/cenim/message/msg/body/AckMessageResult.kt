package com.kamijoucen.cenim.message.msg.body

import com.kamijoucen.cenim.message.msg.MessageResult

class AckMessageResult(private val srcId: Long, val msg: String) : MessageResult() {

    override fun getContent(): String = srcId.toString()

}