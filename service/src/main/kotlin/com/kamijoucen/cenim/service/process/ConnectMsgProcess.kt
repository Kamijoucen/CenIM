package com.kamijoucen.cenim.service.process

import com.kamijoucen.cenim.common.conn.ConsumeResult
import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.service.manager.ServiceContext
import com.kamijoucen.cenim.service.util.UserConnectFuture

internal fun connectMsgProcess() = IMConsumer { msg, ctx ->
    val context = ContextUtil.getBean(ServiceContext::class.java)
    val future = UserConnectFuture()
    context.connectFutureManager.set(msg.body.execute().getContent().toLong(), future)
    val conn = context.routerClientToServiceConnManager.getConn(ctx)
    if (conn != null) {
        context.cacheManager.set(
                MappingKeyGenerator.routerToService(msg.body.execute().getContent()),
                conn.getId())
    }
    future.isSuccess = true
    return@IMConsumer ConsumeResult(true)
}