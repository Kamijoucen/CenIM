package com.kamijoucen.cenim.router.process

import com.kamijoucen.cenim.common.domain.ConsumeResult
import com.kamijoucen.cenim.common.domain.IMConn
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
            // todo 考虑在集群环境下，client可能连接到多个router，这里后期要做处理
            // 将client到router的映射记录入缓存
            context.cacheManager.set(
                    MappingKeyGenerator.userToRouterKey(msg.header.fromId.toString()),
                    ctx.channel().attr(IMConn.NET_ID).get().toString())
            // 用户ID与用户登录到的service层的连接ID（netId）的映射记录入缓存
            context.cacheManager.set(
                    MappingKeyGenerator.userToServiceKey(msg.header.fromId.toString()),
                    conn.getId())
            ContextUtil.getBean(MsgSender::class.java).sendMsg(msg, conn)
        }
        return@IMConsumer ConsumeResult(true, next = false)
    } else {
        return@IMConsumer ConsumeResult(false, next = false)
    }
}