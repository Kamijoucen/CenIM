package com.kamijoucen.cenim.router.service

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.domain.ClientToRouterConn
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserStatusService {

    @Autowired
    lateinit var context: RouterContext

    fun online(msg: Message, ctx: ChCtx): Boolean {
        val userId = msg.body.execute().getContent()
        val clientConn = context.clientToRouterConnManager.getConn(ctx)
        if (clientConn != null) {
            // todo 考虑在集群环境下，client可能连接到多个router，这里后期要做处理
            // 将client到router的映射记录入缓存
            context.cacheManager.set(
                    MappingKeyGenerator.clientToUser(userId),
                    clientConn.getId())
        } else {
            TODO()
        }
        // todo offline msg
        return true
    }

    fun offline(userId: String) {
        TODO()
    }

}