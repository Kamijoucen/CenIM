package com.kamijoucen.cenim.message.msg.util

import com.kamijoucen.cenim.common.conn.ConsumeResult
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message

fun interface IMConsumer {
    fun accept(msg: Message, ctx: ChCtx): ConsumeResult
}