package com.kamijoucen.cenim.service.handler

import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import com.kamijoucen.cenim.service.domain.RouterClientToServiceConn
import com.kamijoucen.cenim.service.manager.ServiceContext
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.stereotype.Component


@Component
class RouterClientToServiceHandler : SimpleChannelInboundHandler<Message>() {

    private lateinit var serviceContext: ServiceContext

    override fun channelRead0(context: ChannelHandlerContext, msg: Message) {
        val netId = serviceContext.cacheManager.get(MappingKeyGenerator.userToServiceKey(msg.header.destId.toString()))
        var conn = serviceContext.routerClientToServiceConnManager.getConn(netId!!)

    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        serviceContext.routerClientToServiceConnManager.addConn(RouterClientToServiceConn(ctx))
    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        super.channelInactive(ctx)
//        TODO("reconnect...")
    }
}