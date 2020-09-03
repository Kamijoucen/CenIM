package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.domain.ConsumeResult
import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.request.OnlineMessageResult

internal object RequestParseFactory {

    /**
     * 上线消息
     * router层只需验证访客合法性
     */
    fun onlineMsg(): IMConsumer<Message, ChCtx> {
        return IMConsumer { msg, ctx ->
            val result = msg.body.execute() as OnlineMessageResult
            val id = result.getProperty("id")
            if (id != "admin") {
                ContextUtil.getBean(RouterContext::class.java)
                        .clientToRouterConnManager.removeConn(ctx)?.close()
                // TODO: 2020/9/1
                ConsumeResult(false)
            } else ConsumeResult(true)
        }
    }

}