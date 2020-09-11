package com.kamijoucen.cenim.router.service

import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.router.domain.ClientToRouterConn
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.util.MappingKeyGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserStatusService {

    @Autowired
    lateinit var context: RouterContext

    fun online(userId: String, ctx: ChCtx): Boolean {
        // TODO: 2020/9/12 userId
        val serviceConn = context.routerToServiceServerConnManager.getConn()
                ?: return false
        val clientConn = ClientToRouterConn(ctx)
        context.clientToRouterConnManager.addConn(clientConn)
        // todo 考虑在集群环境下，client可能连接到多个router，这里后期要做处理
        // 将client到router的映射记录入缓存
        context.cacheManager.set(
                MappingKeyGenerator.userToRouterKey(userId),
                clientConn.getId())
        // 用户ID与用户登录到的service层的连接ID（netId）的映射记录入缓存
        context.cacheManager.set(
                MappingKeyGenerator.userToServiceKey(userId),
                serviceConn.getId())
        // todo offline msg
        return true
    }

    fun offline(userId: String) {

    }

}