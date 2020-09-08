package com.kamijoucen.cenim.router.process

import com.kamijoucen.cenim.common.domain.ConsumeResult
import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.util.MappingKeyGenerator
import com.kamijoucen.cenim.router.util.MsgSender

internal fun onlineMsgProcess() = IMConsumer<RequestMessage, ChCtx> { msg, ctx ->
    val context = ContextUtil.getBean(RouterContext::class.java)
    val conn = context.routerToServiceServerConnManager.getConn()
    if (conn != null) {
        msg.body.execute().let {
            context.cacheManager.set(MappingKeyGenerator.userHostKey(msg.header.fromId.toString()), conn.getId())
            ContextUtil.getBean(MsgSender::class.java).sendMsg(msg, conn)
        }
        return@IMConsumer ConsumeResult(true, next = false)
    } else {
        return@IMConsumer ConsumeResult(false, next = false)
    }
}