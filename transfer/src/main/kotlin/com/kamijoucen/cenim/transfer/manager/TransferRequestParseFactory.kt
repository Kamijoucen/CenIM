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
     * connector层只需验证访客合法性
     */
    fun onlineMsg(): IMConsumer<Message, ChCtx> {
        return object : IMConsumer<Message, ChCtx> {
            override fun accept(msg: Message, ctx: ChCtx): ConsumeResult {
                val result = msg.body.execute() as OnlineMessageResult

                ContextUtil.getBean(TransferContext::class.java)
                        .connectorClientToTransferConnManager.removeConn(ctx)
                return ConsumeResult(true)
            }
        }
    }


}