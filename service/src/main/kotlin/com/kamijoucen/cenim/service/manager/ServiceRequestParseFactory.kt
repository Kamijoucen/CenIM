package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.common.domain.ConsumeResult
import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.message.msg.Message

internal object ServiceRequestParseFactory {

    /**
     * 上线消息
     * service需要记录 userId:busHostId 的映射关系
     */
    fun onlineMsg(): IMConsumer<Message, ChCtx> {
        return IMConsumer { msg, ctx ->
            val userId = msg.body.execute().getContent()
            val context = ContextUtil.getBean(ServiceContext::class.java)

            // context.cacheManager.set(userId, )

            ConsumeResult(true)
        }
    }


}