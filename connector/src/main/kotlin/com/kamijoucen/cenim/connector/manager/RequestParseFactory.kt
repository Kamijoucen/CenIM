package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message
import kotlin.random.Random

typealias ReqParseFac = RequestParseFactory

class RequestParseFactory {

    companion object {}

}

internal fun RequestParseFactory.Companion.stringMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            val connCtx = ContextUtil.getBean(ConnectorContext::class.java)
            msg.header.msgId = Random.Default.nextLong()
            connCtx.connectorToTransferServerConnManager
                    .randomConn()
                    ?.getCtx()
                    ?.channel()
                    ?.writeAndFlush(msg)
            println(msg.body.execute().getContent())
        }
    }
}

internal fun RequestParseFactory.Companion.customMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
//            TODO("Not yet implemented")
            println("RequestParseFactory.Companion.customMsg")
            println(msg.body.execute().getContent())
        }
    }
}

internal fun RequestParseFactory.Companion.onlineMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            // todo auth
            val connCtx = ContextUtil.getBean(ConnectorContext::class.java)
            msg.header.msgId = Random.Default.nextLong()
            connCtx.connectorToTransferServerConnManager
                    .randomConn()
                    ?.getCtx()
                    ?.writeAndFlush(msg)
        }
    }
}