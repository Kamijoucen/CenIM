package com.kamijoucen.cenim.router.process

import com.kamijoucen.cenim.common.conn.ConsumeResult
import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.router.service.UserStatusService

internal fun onlineMsgProcess() = IMConsumer { msg, ctx ->
    val userStatusService = ContextUtil.getBean(UserStatusService::class.java)
    val success = userStatusService.online(msg, ctx)
    return@IMConsumer ConsumeResult(success, true)
}