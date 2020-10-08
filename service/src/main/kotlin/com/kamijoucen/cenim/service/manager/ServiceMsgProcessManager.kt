package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.message.msg.*
import com.kamijoucen.cenim.service.process.connectMsgProcess
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ServiceMsgProcessManager {

    private val log = LogFactory.getLog("ServiceMsgParseManager")

    private val processMap = ConcurrentHashMap<Int, IMConsumer>()

    init {
        MessageBodyType.values().forEach {
            when (it.type) {
                MessageBodyType.CONNECT_MSG.type ->
                    processMap[MessageBodyType.CONNECT_MSG.type] = connectMsgProcess()
            }
        }
    }

    fun getProcess(type: Int) = processMap[type]

}