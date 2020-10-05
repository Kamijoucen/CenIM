package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.service.util.MsgUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MsgService {

    @Autowired
    private lateinit var serviceContext: ServiceContext

    fun transferMsg(msg: Message) {
        val key = MappingKeyGenerator.routerToService(msg.header.destId.toString())
        val netId = serviceContext.cacheManager.get(key) ?: return
        val conn = serviceContext.routerClientToServiceConnManager.getConn(netId)
        if (conn == null) {
            println("------- ${msg.header.destId} not online")
            serviceContext.cacheManager.del(key)
            // todo put offline msg
            return
        }
        MsgUtil.sendMsg(msg, conn)
    }

}