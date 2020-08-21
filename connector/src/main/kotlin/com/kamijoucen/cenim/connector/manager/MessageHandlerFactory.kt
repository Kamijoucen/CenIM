package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.connector.domain.ClientToConnectorConn
import com.kamijoucen.cenim.message.msg.body.CsmMsgBody
import com.kamijoucen.cenim.message.msg.body.StrMsgBody
import com.kamijoucen.cenim.message.msg.body.request.OlMsgBody
import io.netty.channel.ChannelHandlerContext

typealias ChCtx = ChannelHandlerContext
typealias MsgHandleFac = MessageHandlerFactory

class MessageHandlerFactory {

    companion object {}

}

internal fun MessageHandlerFactory.Companion.stringMsg(): IMConsumer<StrMsgBody, ChCtx> {
    return object : IMConsumer<StrMsgBody, ChCtx> {
        override fun accept(msg: StrMsgBody, ctx: ChCtx) {
            TODO("Not yet implemented")
        }
    }
}

internal fun MessageHandlerFactory.Companion.customMsg(): IMConsumer<CsmMsgBody, ChCtx> {
    return object : IMConsumer<CsmMsgBody, ChCtx> {
        override fun accept(msg: CsmMsgBody, ctx: ChCtx) {
            TODO("Not yet implemented")
        }
    }
}

internal fun MessageHandlerFactory.Companion.onlineMsg(): IMConsumer<OlMsgBody, ChCtx> {
    return object : IMConsumer<OlMsgBody, ChCtx> {
        override fun accept(msg: OlMsgBody, ctx: ChCtx) {
            // TODO: 2020/8/21 auth
            val connCtx = ContextUtil.getBean(ConnectorContext::class.java)

            connCtx.clientConnContextManager.put(ClientToConnectorConn(ctx))
        }
    }
}