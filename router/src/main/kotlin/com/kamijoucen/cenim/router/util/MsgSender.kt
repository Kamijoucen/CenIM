package com.kamijoucen.cenim.router.util

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.domain.RouterToServiceServerConn
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MsgSender {

    private val log = LogFactory.getLog("MsgSender")

    @Autowired
    private lateinit var routerContext: RouterContext

    fun sendMsg(msg: Message) {
        val netId = routerContext.cacheManager.get(MappingKeyGenerator.userToServiceKey(msg.header.destId.toString()))
        if (netId != null) {
            val conn = routerContext.routerToServiceServerConnManager.getConn(netId)
            if (conn != null && conn.getCtx().channel().isOpen) {
                conn.getCtx().channel().writeAndFlush(msg)
            } else {
                log.error("router to service conn not fount! netId:${netId}")
            }
        } else {
            log.error("user to service mapping not fount! netId:${netId}")
        }
    }

    fun sendMsg(msg: Message, conn: RouterToServiceServerConn) {
        if (conn.getCtx().channel().isOpen) {
            conn.getCtx().channel().writeAndFlush(msg)
        }
    }
}