package com.kamijoucen.cenim.router.service

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.domain.ClientToRouterConn
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserStatusService {

    @Autowired
    lateinit var context: RouterContext

    fun online(msg: Message, ctx: ChCtx): Boolean {
        val userId = msg.body.execute().getContent()

        val clientConn = ClientToRouterConn(ctx).let {
            context.clientToRouterConnManager.addConn(it)
        }
        context.cacheManager.set(
                MappingKeyGenerator.clientToUser(userId),
                clientConn.getId())
        // 初始化历史记录
        context.chatHistoryManager.init(userId.toLong())
        context.chatHistoryManager.setHistory(userId.toLong(), msg)

        // todo offline msg
        return true
    }

    fun offline(userId: String) {
        TODO()
    }

}