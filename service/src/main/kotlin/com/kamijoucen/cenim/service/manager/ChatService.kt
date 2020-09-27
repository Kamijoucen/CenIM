package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.service.util.MsgSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChatService {

    @Autowired
    private lateinit var msgSender: MsgSender

    @Autowired
    private lateinit var serviceContext: ServiceContext

    fun doChat(msg: Message) {
        val key = MappingKeyGenerator.routerToService(msg.header.destId.toString())
        val netId = serviceContext.cacheManager.get(key)
                ?: return
        val conn = serviceContext.routerClientToServiceConnManager.getConn(netId)
        if (conn == null) {
            serviceContext.cacheManager.del(key)
            // todo put offline msg
            return
        }
        msgSender.sendMsg(msg, conn)
    }

}