package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.*
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class RouterMsgParseManager {

    private val log = LogFactory.getLog("RouterMsgParseManager")

    private val requestParseMap = ConcurrentHashMap<Int, IMConsumer<in RequestMessage, ChCtx>>()

    private val responseParseMap = ConcurrentHashMap<Int, IMConsumer<in ResponseMessage, ChCtx>>()

    init {
        RequestBodyType.values().forEach {
            when (it.type) {
                RequestBodyType.ONLINE_MSG.type -> RequestParseFactory.onlineMsg()
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