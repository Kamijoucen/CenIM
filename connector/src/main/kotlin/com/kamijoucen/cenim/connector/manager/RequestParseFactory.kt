package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.connector.conn.ClientToConnectorConn
import com.kamijoucen.cenim.connector.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.StringMessageResult

typealias ReqParseFac = RequestParseFactory

class RequestParseFactory {

    companion object {}

}

internal fun RequestParseFactory.Companion.stringMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            var result: StringMessageResult = msg.body.execute() as StringMessageResult
            TODO("Not yet implemented")
        }
    }
}

internal fun RequestParseFactory.Companion.customMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            TODO("Not yet implemented")
        }
    }
}

internal fun RequestParseFactory.Companion.onlineMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            // TODO: 2020/8/21 auth
            val connCtx = ContextUtil.getBean(ConnectorContext::class.java)

            connCtx.clientConnContextManager.addConn(ClientToConnectorConn(ctx))
        }
    }
}