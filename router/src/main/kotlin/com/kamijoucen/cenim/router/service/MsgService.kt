package com.kamijoucen.cenim.router.service

import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.util.MsgUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MsgService {

    @Autowired
    private lateinit var routerContext: RouterContext

    /**
     * 将消息随机发送到某个 service 节点（service层是无状态的）
     */
    fun sendMsg(msg: Message) {

        // todo 如果目标登陆在本地，则不进行转发
//        val destId = msg.header.destId
//        routerContext.clientToRouterConnManager.getConn()

        val conn = routerContext.routerToServiceServerConnManager.getConn()
        if (conn != null) {
            MsgUtil.sendMsg(msg, conn)
        } else {
//            log.error("router to service conn not fount!")
        }
    }

    /**
     * 将消息发送给 client
     */
    fun receiveMsg(msg: Message) {

        val netId = routerContext.cacheManager.get(
                MappingKeyGenerator.clientToUser(msg.header.destId.toString())) ?: return
        val conn = routerContext.clientToRouterConnManager.getConn(netId)
        if (conn != null) {
            MsgUtil.sendMsg(msg, conn)
        } else {
            TODO()
        }
    }

}