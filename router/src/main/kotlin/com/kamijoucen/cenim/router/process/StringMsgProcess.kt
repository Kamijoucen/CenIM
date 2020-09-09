package com.kamijoucen.cenim.router.process

import com.kamijoucen.cenim.common.conn.ConsumeResult
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.util.MappingKeyGenerator
import com.kamijoucen.cenim.router.util.MsgSender
import org.apache.commons.logging.LogFactory

private val log = LogFactory.getLog("StringMsgProcess.kt")

internal fun stringMsgProcess() = IMConsumer { msg, ctx ->

    val context = ContextUtil.getBean(RouterContext::class.java)
    val netId = context.cacheManager.get(
            MappingKeyGenerator.userToServiceKey(msg.header.destId.toString()))
    if (netId != null) {
        val conn = context.routerToServiceServerConnManager.getConn(netId)
        if (conn != null) {
            ContextUtil.getBean(MsgSender::class.java).sendMsg(msg, conn)
            return@IMConsumer ConsumeResult(true, next = false)
        } else {
            log.error("to service conn not found! fromId:${msg.header.fromId}, netId:${netId}")
        }
    } else {

    }
    return@IMConsumer ConsumeResult(false, next = false)

}