package com.kamijoucen.cenim.router.util

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.common.util.IdGenerator
import com.kamijoucen.cenim.message.msg.MessageHeader
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.ResponseBodyType
import com.kamijoucen.cenim.message.msg.ResponseMessage
import com.kamijoucen.cenim.message.msg.body.response.AckMsgBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AckSender {

    @Autowired
    private lateinit var msgAnswer: MsgAnswer

    fun ack(msg: RequestMessage, ctx: ChCtx) {
        val ack = getAck(msg.header.msgId)
        msgAnswer.answerMsg(ack, ctx)
    }

    private fun getAck(srcId: Long): ResponseMessage {
        // TODO: 2020/8/31
        val header = MessageHeader(1, ResponseBodyType.ACK_MSG.type, 1, 1, IdGenerator.nextId())
        val body = AckMsgBody(srcId)
        return ResponseMessage(header, body)
    }
}