package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.message.msg.*
import com.kamijoucen.cenim.service.process.onlineMsgProcess
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ServiceMsgProcessManager {

    private val log = LogFactory.getLog("ServiceMsgParseManager")

    private val requestProcessMap = ConcurrentHashMap<Int, IMConsumer>()

    init {
        MessageBodyType.values().forEach {
            when (it.type) {
                MessageBodyType.ONLINE_MSG.type ->
                    requestProcessMap[MessageBodyType.ONLINE_MSG.type] = onlineMsgProcess()
            }
        }

    }

    fun getRequestProcess(type: Int) = requestProcessMap[type]

}