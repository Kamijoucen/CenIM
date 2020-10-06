package com.kamijoucen.cenim.router.service

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.domain.ClientToRouterConn
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.router.domain.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserStatusService {

    @Autowired
    lateinit var context: RouterContext

    fun online(msg: Message, ctx: ChCtx): UserImpl {
        val userId = msg.body.execute().getContent()
        // todo select user to db
        val user = UserImpl(userId.toLong(), "user+${userId}", -1)
        // 将登录的用户放入上下文
        context.userManager.put(user)
        // 建立客户端到服务器的连接
        val clientConn = ClientToRouterConn(ctx)
        // 将连接放入缓存
        context.clientToRouterConnManager.addConn(clientConn)
        // 将用户与登录到的router节点的连接id放入缓存
        context.cacheManager.set(
                MappingKeyGenerator.clientToUser(userId),
                clientConn.getId())
        // 初始化历史记录
        context.chatHistoryManager.init(userId.toLong())
        context.chatHistoryManager.setHistory(userId.toLong(), msg)
        // todo offline msg
        return user
    }

    fun offline(userId: String) {
        TODO()
    }

}