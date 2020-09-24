package com.kamijoucen.cenim.service.process

import com.kamijoucen.cenim.common.conn.ConsumeResult
import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.service.domain.RouterClientToServiceConn
import com.kamijoucen.cenim.service.manager.ServiceContext

internal fun onlineMsgProcess() = IMConsumer { msg, ctx ->
    val conn = RouterClientToServiceConn(ctx)
    val context = ContextUtil.getBean(ServiceContext::class.java)
    context.routerClientToServiceConnManager.addConn(conn)
    context.cacheManager.set(
            MappingKeyGenerator.userToServiceKey(msg.body.execute().getContent()), conn.getId())
    return@IMConsumer ConsumeResult(true, next = false)
}