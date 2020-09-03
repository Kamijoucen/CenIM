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

    override fun channelInactive(ctx: ChannelHandlerContext?) {
//        super.channelInactive(ctx)
        if (ctx == null) {
            return
        }
        try {
//            TODO("reconnect")
        } catch (ex: Exception) {
            connContext.routerToServiceServerConnManager.removeConn(ctx)
        }
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        if (ctx != null) {
            connContext.routerToServiceServerConnManager.addConn(RouterToServiceServerConn(ctx))
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        if (ctx != null) {
            connContext.routerToServiceServerConnManager.removeConn(ctx)
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ResponseMessage?) {
        println("aaaagg")
        if (ctx == null || msg == null) {
            return
        }
        println("111111111111111111111-----------222222222222")
    }
}
