package com.kamijoucen.cenim.router.service

import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.domain.UserImpl
import com.kamijoucen.cenim.router.exception.HistoryNotFoundException
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.util.MsgUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserChatService {

    @Autowired
    private lateinit var routerContext: RouterContext

    /**
     * 发送消息
     * @param msg 待发送的消息
     */
    fun senMsg(msg: Message) {
        routerContext.userManager.get(msg.header.fromId)?.waitConnect().let {
            if (it != true) {
                TODO("offline ..")
            }
        }
        routerContext.chatHistoryManager.setHistory(msg.header.fromId, msg)
        val destUser = routerContext.userManager.get(msg.header.destId)
        // 如果目标用户登陆在本节点则不进行转发消息
        if (destUser != null) {
            receiveMsg(msg)
            return
        }
        val conn = routerContext.routerToServiceServerConnManager.getConn()
        if (conn != null) {
            MsgUtil.sendMsg(msg, conn)
        } else {
//            log.error("router to service conn not fount!")
        }
    }

    /**
     * @param msg 待接收的消息
     */
    fun receiveMsg(msg: Message) {
        try {
            routerContext.chatHistoryManager.setHistory(msg.header.destId, msg)
        } catch (ex: HistoryNotFoundException) {
            TODO("offline")
        }
        val netId = routerContext.cacheManager.get(
                MappingKeyGenerator.clientToUser(msg.header.destId.toString())) ?: return
        val conn = routerContext.clientToRouterConnManager.getConn(netId)
        if (conn != null) {
            MsgUtil.sendMsg(msg, conn)
        } else {
            TODO("offline")
        }
    }

}