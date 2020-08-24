package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.connector.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.StringMessageResult

typealias ResParseFac = ResponseParseFactory

class ResponseParseFactory {

    companion object {}

}

internal fun ResponseParseFactory.Companion.stringMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            var result: StringMessageResult = msg.body.execute() as StringMessageResult
            TODO("Not yet implemented")
        }
    }
}

internal fun ResponseParseFactory.Companion.customMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            TODO("Not yet implemented")
        }
    }
}

internal fun ResponseParseFactory.Companion.ackMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            TODO("Not yet implemented")
        }
    }
}