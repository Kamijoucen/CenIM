package com.kamijoucen.cenim.connector

import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.connector.handler.ConnectorToTransferHandler
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.client.ClientMessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.client.ClientMessageProtocolEncoder
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

fun startConnectorClient(config: ConnectorConfig): Boolean {
    val transferUrls = config.transfer
    transferUrls.forEach {
        var url = it.split(":")
        startClient(url[0], url[1])
    }
    return false
}

private fun startClient(host: String, port: String) {
    val bootstrap = Bootstrap()
    bootstrap.channel(NioSocketChannel::class.java)

    val group = NioEventLoopGroup()
    bootstrap.group(group)
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
                pipeline.addLast(ContextUtil.getBean(ConnectorToTransferHandler::class.java))
            }
        })
        val channelFuture = bootstrap.connect(host, Integer.parseInt(port))
        channelFuture.sync()
    } finally {
        group.shutdownGracefully()
    }

}