package com.kamijoucen.cenim.connector.service

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.connector.manager.ConnectorContext
import com.kamijoucen.cenim.connector.manager.ConnectorToTransferServerConnManager
import com.kamijoucen.cenim.message.msg.RequestMessage
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MsgSender {

    private val log = LogFactory.getLog("MsgSender")

    @Autowired
    private lateinit var connectorContext: ConnectorContext

    fun sendMsg(msg: RequestMessage, ctx: ChCtx) {
        val conn = connectorContext.connectorToTransferServerConnManager.getConn()
        if (conn == null) {
            log.error("connector to transfer conn not fount!")
            return
        }
        conn.getCtx().channel().writeAndFlush(msg)
    }
}