package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.connector.conn.ClientToConnectorConn
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.CsmMsgBody
import com.kamijoucen.cenim.message.msg.body.StrMsgBody
import com.kamijoucen.cenim.message.msg.body.StringMessageResult
import com.kamijoucen.cenim.message.msg.body.request.OlMsgBody
import io.netty.channel.ChannelHandlerContext

typealias ChCtx = ChannelHandlerContext
typealias MsgHandleFac = MessageHandlerFactory

class MessageHandlerFactory {

    companion object {}

}

internal fun MessageHandlerFactory.Companion.stringMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            var result: StringMessageResult = msg.body.execute() as StringMessageResult
            TODO("Not yet implemented")
        }
    }
}

internal fun MessageHandlerFactory.Companion.customMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            TODO("Not yet implemented")
        }
    }
}

internal fun MessageHandlerFactory.Companion.onlineMsg(): IMConsumer<Message, ChCtx> {
    return object : IMConsumer<Message, ChCtx> {
        override fun accept(msg: Message, ctx: ChCtx) {
            // TODO: 2020/8/21 auth
            val connCtx = ContextUtil.getBean(ConnectorContext::class.java)

            connCtx.clientConnContextManager.addConn(ClientToConnectorConn(ctx))
        }
    }
}