package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.*
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ServiceMsgParseManager {

    private val log = LogFactory.getLog("ServiceMsgParseManager")

    private val requestParseMap = ConcurrentHashMap<Int, IMConsumer>()

    init {
        MessageBodyType.values().forEach {
//            when (it.type) {
//                RequestBodyType.ONLINE_MSG.type ->
//                    requestParseMap[RequestBodyType.ONLINE_MSG.type] =
//                            ServiceRequestParseFactory.onlineMsg()
//            }
        }

    }

    fun getRequestParse(type: Int) = requestParseMap[type]

}