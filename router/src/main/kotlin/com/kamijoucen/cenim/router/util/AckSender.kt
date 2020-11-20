package com.kamijoucen.cenim.router.util

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.common.util.idgen.IdGenerator
import com.kamijoucen.cenim.common.util.idgen.SnowflakeMsgIdGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.MessageBodyType
import com.kamijoucen.cenim.message.msg.MessageHeader
import com.kamijoucen.cenim.message.msg.MsgVersion
import com.kamijoucen.cenim.message.msg.body.AckMsgBody
import com.kamijoucen.cenim.router.manager.IdGeneratorManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AckSender {

    @Autowired
    lateinit var idGeneratorManager: IdGeneratorManager

    fun ack(msg: Message, ctx: ChCtx) {
        val ack = getAck(msg)
        MsgUtil.sendMsg(ack, ctx)
    }

    private fun getAck(msg: Message): Message {
        // TODO: 2020/8/31
        val header = MessageHeader(
                MsgVersion.V1.version, MessageBodyType.ACK_MSG.type,
                msg.header.fromId, msg.header.fromId,
                idGeneratorManager.msgIdGenerator.nextId(MessageBodyType.ACK_MSG.type.toLong()))
        val body = AckMsgBody(msg.header.msgId)
        return Message(header, body)
    }
}