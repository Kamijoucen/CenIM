package com.kamijoucen.cenim.transfer.manager

import com.kamijoucen.cenim.common.domain.ConsumeResult
import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.request.OnlineMessageResult

internal object TransferRequestParseFactory {

    /**
     * 上线消息
     * transfer需要记录 userId:busHostId 的映射关系
     */
    fun onlineMsg(): IMConsumer<Message, ChCtx> {
        return object : IMConsumer<Message, ChCtx> {
            override fun accept(msg: Message, ctx: ChCtx): ConsumeResult {
                val userId = msg.body.execute().getContent()
                val context = ContextUtil.getBean(TransferContext::class.java)

                // context.cacheManager.set(userId, )

                return ConsumeResult(true)
            }
        }
    }


}