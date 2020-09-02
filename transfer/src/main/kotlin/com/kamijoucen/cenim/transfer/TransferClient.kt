package com.kamijoucen.cenim.transfer

import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.client.ClientMessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.client.ClientMessageProtocolEncoder
import com.kamijoucen.cenim.transfer.handler.TransferToBusinessServerHandler
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

internal fun startBusinessClient(config: TransferConfig): Boolean {
    val businessUrl = config.business
    businessUrl.forEach {
        it.split(":").let { url ->
            startClient(url[0], url[1])
        }
    }
    return false
}

private fun startClient(host: String, port: String) {
    val bootstrap = Bootstrap()
    bootstrap.channel(NioSocketChannel::class.java).group(NioEventLoopGroup())
    try {
        bootstrap.handler(object : ChannelInitializer<NioSocketChannel?>() {
            @Throws(Exception::class)
            override fun initChannel(ch: NioSocketChannel?) {
                if (ch == null) {
                    return
                }
                val pipeline: ChannelPipeline = ch.pipeline()
                pipeline.addLast(MessageFrameDecoder())
                pipeline.addLast(MessageFrameEncoder())
                pipeline.addLast(ClientMessageProtocolDecoder())
                pipeline.addLast(ClientMessageProtocolEncoder())
                pipeline.addLast(ContextUtil.getBean(TransferToBusinessServerHandler::class.java))
            }
        })
        val channelFuture = bootstrap.connect(host, Integer.parseInt(port))
        channelFuture.addListener() {
            if (it.isSuccess) it.cause().printStackTrace()
        }
        channelFuture.sync()
    } finally {
//        group.shutdownGracefully()
    }

}