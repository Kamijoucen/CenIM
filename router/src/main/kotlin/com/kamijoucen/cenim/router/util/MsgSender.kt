package com.kamijoucen.cenim.router.util

import com.kamijoucen.cenim.common.util.MappingKeyGenerator
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
        val conn = routerContext.routerToServiceServerConnManager.getConn()
        if (conn != null) {
            sendMsg(msg, conn)
        } else {
            log.error("router to service conn not fount!")
        }
    }

    fun sendMsg(msg: Message, conn: RouterToServiceServerConn) {
        if (conn.getCtx().channel().isOpen) {
            conn.getCtx().channel().writeAndFlush(msg)
        } else {
            TODO()
        }
    }
}