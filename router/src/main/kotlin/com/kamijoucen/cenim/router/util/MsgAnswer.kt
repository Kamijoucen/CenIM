package com.kamijoucen.cenim.router.util

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.ResponseMessage
import org.springframework.stereotype.Component

@Component
class MsgAnswer {

    fun answerMsg(msg: ResponseMessage, ctx: ChCtx) {
        if (ctx.channel().isOpen) {
            ctx.channel().writeAndFlush(msg)
        }
    }

}