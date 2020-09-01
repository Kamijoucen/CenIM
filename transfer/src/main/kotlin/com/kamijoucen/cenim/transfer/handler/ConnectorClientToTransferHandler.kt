package com.kamijoucen.cenim.transfer.handler

import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.ResponseMessage
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import com.kamijoucen.cenim.transfer.domain.ConnectorClientToTransferConn
import com.kamijoucen.cenim.transfer.manager.TransferContext
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component


@Component
class ConnectorClientToTransferHandler : SimpleChannelInboundHandler<RequestMessage>() {

    private lateinit var transferContext: TransferContext

    override fun channelRead0(context: ChannelHandlerContext, msg: RequestMessage) {

        val result = msg.body.execute()
        println("---------------------MSG---------------------")
        println(result.getContent())
        var responseMessage = ResponseMessage(msg.header, StringMessageBody("这是响应文本"))
        context.writeAndFlush(responseMessage)
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        transferContext.connectorClientToTransferConnManager.addConn(ConnectorClientToTransferConn(ctx))
    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        super.channelInactive(ctx)
//        TODO("reconnect...")
    }
}