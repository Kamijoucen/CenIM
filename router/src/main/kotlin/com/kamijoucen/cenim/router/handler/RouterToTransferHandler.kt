package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.router.domain.RouterToServiceServerConn
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.message.msg.ResponseMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RouterToServiceHandler : SimpleChannelInboundHandler<ResponseMessage>() {

    @Autowired
    lateinit var connContext: RouterContext

    override fun channelInactive(ctx: ChannelHandlerContext) {
        try {
//            TODO("reconnect")
        } catch (ex: Exception) {
            connContext.routerToServiceServerConnManager.removeConn(ctx)
        }
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        connContext.routerToServiceServerConnManager.addConn(RouterToServiceServerConn(ctx))
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        connContext.routerToServiceServerConnManager.removeConn(ctx)
    }

    override fun channelRead0(ctx: ChannelHandlerContext, msg: ResponseMessage) {

    }
}
