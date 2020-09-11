package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.message.msg.*
import com.kamijoucen.cenim.router.process.onlineMsgProcess
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component

@Component
class RouterMsgProcessManager {

    private val log = LogFactory.getLog("RouterMsgProcessManager")

    private val requestProcess = HashMap<Int, IMConsumer>()

    private val responseProcess = HashMap<Int, IMConsumer>()

    init {
        MessageBodyType.values().forEach {
            when (it.type) {
                MessageBodyType.ONLINE_MSG.type -> requestProcess[it.type] = onlineMsgProcess()
                else -> log.warn("msg type not have process! type:${it.type}")
            }
        }
    }

    fun getRequestParse(type: Int) = requestProcess[type]

    fun getResponseParse(type: Int) = responseProcess[type]

}