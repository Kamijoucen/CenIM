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
        val netId = serviceContext.cacheManager.get(MappingKeyGenerator.userToServiceKey(msg.header.destId.toString()))
                ?: return
        val conn = serviceContext.routerClientToServiceConnManager.getConn(netId)
        if (conn == null) {
            serviceContext.cacheManager.del(MappingKeyGenerator.userToServiceKey(msg.header.destId.toString()))
            // todo put offline msg
            return
        }
        msgSender.sendMsg(msg, conn)
    }

}