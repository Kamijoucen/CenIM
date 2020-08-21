package com.kamijoucen.cenim.connector.handler

import com.kamijoucen.cenim.message.msg.RequestMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.stereotype.Component

@Component
class ConnectorToTransferHandler : SimpleChannelInboundHandler<RequestMessage>() {

    override fun channelRead0(p0: ChannelHandlerContext?, p1: RequestMessage?) {



        TODO("Not yet implemented")
    }
}