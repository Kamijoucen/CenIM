package com.kamijoucen.cenim.router.process

import com.kamijoucen.cenim.common.conn.ConsumeResult
import com.kamijoucen.cenim.message.msg.util.IMConsumer
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.router.service.UserChatService
import com.kamijoucen.cenim.router.service.UserStatusService

internal fun onlineMsgProcess() = IMConsumer { msg, ctx ->
    val userStatusService = ContextUtil.getBean(UserStatusService::class.java)
    val userChatService = ContextUtil.getBean(UserChatService::class.java)
    // 用户上线
    userStatusService.online(msg, ctx)
    // 将上线消息转发到service层
    userChatService.senMsg(msg)
    return@IMConsumer ConsumeResult(true)
}