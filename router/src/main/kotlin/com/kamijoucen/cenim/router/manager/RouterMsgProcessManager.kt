package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.*
import com.kamijoucen.cenim.router.process.onlineMsgProcess
import com.kamijoucen.cenim.router.process.stringMsgProcess
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
                MessageBodyType.STRING_MSG.type -> requestProcess[it.type] = stringMsgProcess()
                else -> log.warn("msg type not have process! type:${it.type}")
            }
        }
    }

    fun getRequestParse(type: Int) = requestProcess[type]

    fun getResponseParse(type: Int) = responseProcess[type]

}