package com.kamijoucen.cenim.router.util

import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.message.msg.RequestMessage
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MsgSender {

    private val log = LogFactory.getLog("MsgSender")

    @Autowired
    private lateinit var routerContext: RouterContext

    fun sendMsg(msg: RequestMessage) {
        val conn = routerContext.routerToServiceServerConnManager.getConn()
        if (conn == null) {
            log.error("router to service conn not fount!")
            return
        }
        if (conn.getCtx().channel().isOpen) {
            conn.getCtx().channel().writeAndFlush(msg)
        }
    }
}