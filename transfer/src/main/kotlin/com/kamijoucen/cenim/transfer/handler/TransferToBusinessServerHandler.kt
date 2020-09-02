package com.kamijoucen.cenim.transfer.handler

import com.kamijoucen.cenim.message.msg.ResponseMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.stereotype.Component

@Component
class TransferToBusinessServerHandler : SimpleChannelInboundHandler<ResponseMessage>() {

    

    override fun channelRead0(ctx: ChannelHandlerContext, msg: ResponseMessage) {
        TODO("Not yet implemented")
    }


}