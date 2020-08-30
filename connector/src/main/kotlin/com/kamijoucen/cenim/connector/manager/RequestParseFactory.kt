package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message

typealias ReqParseFac = RequestParseFactory

class RequestParseFactory {

    companion object {}

}

internal fun RequestParseFactory.Companion.stringMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            print("RequestParseFactory.Companion.stringMsg  ")
            println(msg.body.execute().getContent())
        }
    }
}

internal fun RequestParseFactory.Companion.customMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
//            TODO("Not yet implemented")
            println("RequestParseFactory.Companion.customMsg  ")
            println(msg.body.execute().getContent())
        }
    }
}

internal fun RequestParseFactory.Companion.onlineMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
        }
    }
}