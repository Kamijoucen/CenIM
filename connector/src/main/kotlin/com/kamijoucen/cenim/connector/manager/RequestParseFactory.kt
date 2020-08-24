package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.connector.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.StringMessageResult
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
            connCtx.connectorToTransferConnManager
                    .randomConn()
                    ?.getCtx()
                    ?.writeAndFlush(msg)
        }
    }
}

internal fun RequestParseFactory.Companion.customMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
//            TODO("Not yet implemented")
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
            connCtx.connectorToTransferConnManager
                    .randomConn()
                    ?.getCtx()
                    ?.writeAndFlush(msg)
        }
    }
}