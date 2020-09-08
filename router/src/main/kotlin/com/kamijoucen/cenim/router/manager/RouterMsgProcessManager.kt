package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.*
import com.kamijoucen.cenim.router.process.onlineMsgProcess
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class RouterMsgProcessManager {

    private val log = LogFactory.getLog("RouterMsgProcessManager")

    private val requestParseMap = HashMap<Int, IMConsumer<in RequestMessage, ChCtx>>()

    private val responseParseMap = HashMap<Int, IMConsumer<in ResponseMessage, ChCtx>>()

    init {
        RequestBodyType.values().forEach {
            when (it.type) {
                RequestBodyType.ONLINE_MSG.type -> onlineMsgProcess()
                else -> log.warn("msg type not have process! type:${it.type}")
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