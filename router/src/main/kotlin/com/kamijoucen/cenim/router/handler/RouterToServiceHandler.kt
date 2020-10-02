package com.kamijoucen.cenim.router.handler

import com.kamijoucen.cenim.common.util.JsonUtil
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.domain.RouterToServiceServerConn
import com.kamijoucen.cenim.router.manager.RouterContext
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@ChannelHandler.Sharable
class RouterToServiceHandler : SimpleChannelInboundHandler<Message>() {

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

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        println("RouterToServiceHandler =========\t" + JsonUtil.toJson(msg))
    }
}
