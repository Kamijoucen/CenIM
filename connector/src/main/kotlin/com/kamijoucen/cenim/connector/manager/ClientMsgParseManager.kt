package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.message.msg.MessageBody
import com.kamijoucen.cenim.message.msg.RequestBodyType
import com.kamijoucen.cenim.message.msg.body.CsmMsgBody
import com.kamijoucen.cenim.message.msg.body.StrMsgBody
import com.kamijoucen.cenim.message.msg.body.request.OlMsgBody
import org.springframework.stereotype.Component

@Component
class ClientMsgParseManager {

    private val parseMap = HashMap<Class<out MessageBody>, IMConsumer<out MessageBody, ChCtx>>()

    init {
        RequestBodyType.values().forEach {
            when (it.opClass) {
                StrMsgBody::class.java ->
                    parseMap[it.opClass] = MsgHandleFac.stringMsg()
                CsmMsgBody::class.java ->
                    parseMap[it.opClass] = MsgHandleFac.customMsg()
                OlMsgBody::class.java ->
                    parseMap[it.opClass] = MsgHandleFac.onlineMsg()
                else ->
                    TODO("no handler type: ${it.type}")
            }
        }
    }


}