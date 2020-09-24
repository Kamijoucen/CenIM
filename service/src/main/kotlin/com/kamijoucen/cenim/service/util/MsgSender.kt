package com.kamijoucen.cenim.service.util

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.common.util.IdGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.MessageBodyType
import com.kamijoucen.cenim.message.msg.MessageHeader
import com.kamijoucen.cenim.message.msg.body.AckMsgBody
import com.kamijoucen.cenim.service.domain.RouterClientToServiceConn
import org.springframework.stereotype.Component

@Component
class MsgSender {

    fun sendMsg(msg: Message, conn: RouterClientToServiceConn) {
        if (conn.getCtx().channel().isOpen) {
            conn.getCtx().channel().writeAndFlush(msg)
        }
    }

    fun sendMsg(msg: Message, ctx: ChCtx) {
        if (ctx.channel().isOpen) {
            ctx.channel().writeAndFlush(msg)
        }
    }

    fun ack(msgId: Long, ctx: ChCtx) {
        val msg = getAck(msgId)
        sendMsg(msg, ctx)
    }

    private fun getAck(srcId: Long): Message {
        // TODO: 2020/8/31
        val header = MessageHeader(1, MessageBodyType.ACK_MSG.type, 1, 1, IdGenerator.nextId())
        val body = AckMsgBody(srcId)
        return Message(header, body)
    }

}