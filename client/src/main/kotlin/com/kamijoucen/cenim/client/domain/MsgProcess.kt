package com.kamijoucen.cenim.client.domain

import com.kamijoucen.cenim.message.msg.Message

interface MsgProcess {

    fun process(msg: Message)

}