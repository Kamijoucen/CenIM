package com.kamijoucen.cenim.router.process

import com.kamijoucen.cenim.common.conn.ConsumeResult
import com.kamijoucen.cenim.common.conn.IMConn
import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.service.UserStatusService
import com.kamijoucen.cenim.router.util.MappingKeyGenerator
import com.kamijoucen.cenim.router.util.MsgSender
import reactor.util.function.Tuple5

internal fun onlineMsgProcess() = IMConsumer { msg, ctx ->
    val userStatusService = ContextUtil.getBean(UserStatusService::class.java)
    val success = userStatusService.online(msg.body.execute().getContent(), ctx)
    if (success) {
        return@IMConsumer ConsumeResult(false, next = false)
    }
    return@IMConsumer ConsumeResult(true, next = false)
}