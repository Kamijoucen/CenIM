package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.message.msg.MessageBody
import com.kamijoucen.cenim.message.msg.RequestBodyType
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.ResponseMessage
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ClientMsgParseManager {

    private val requestParseMap = ConcurrentHashMap<Int, IMConsumer<in RequestMessage, ChCtx>>()

    private val responseParseMap = ConcurrentHashMap<Int, IMConsumer<in ResponseMessage, ChCtx>>()

    init {
        RequestBodyType.values().forEach {
            when (it.type) {
                RequestBodyType.STRING_MSG.type ->
                    requestParseMap[it.type] = MsgHandleFac.stringMsg()
                RequestBodyType.CUSTOM_MSG.type ->
                    requestParseMap[it.type] = MsgHandleFac.customMsg()
                RequestBodyType.CUSTOM_MSG.type ->
                    requestParseMap[it.type] = MsgHandleFac.onlineMsg()
                else ->
                    TODO("no handler type: ${it.type}")
            }
        }
    }

    fun getRequestParse(type: Int) = requestParseMap[type]!!

    fun getResponseParse(type: Int) = responseParseMap[type]!!

}