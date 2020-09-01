package com.kamijoucen.cenim.transfer.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.RequestBodyType
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.ResponseBodyType
import com.kamijoucen.cenim.message.msg.ResponseMessage
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class TransferMsgParseManager {

    private val log = LogFactory.getLog("ConnectorMsgParseManager")

    private val requestParseMap = ConcurrentHashMap<Int, IMConsumer<in RequestMessage, ChCtx>>()

    private val responseParseMap = ConcurrentHashMap<Int, IMConsumer<in ResponseMessage, ChCtx>>()

    init {
        RequestBodyType.values().forEach {
            when (it.type) {
                RequestBodyType.ONLINE_MSG.type -> TransferRequestParseFactory.onlineMsg()
            }
        }

        ResponseBodyType.values().forEach {
            when (it.type) {
            }
        }
    }

    fun getRequestParse(type: Int) = requestParseMap[type]

    fun getResponseParse(type: Int) = responseParseMap[type]

}