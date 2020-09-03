package com.kamijoucen.cenim.router

import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.router.handler.RouterToServiceHandler
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.client.ClientMessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.client.ClientMessageProtocolEncoder
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import org.apache.commons.logging.LogFactory

private val LOG = LogFactory.getLog("RouterClient")

internal fun startRouterClient(config: RouterConfig): Boolean {
    val serviceUrls = config.service
    serviceUrls.forEach {
        val url = it.split(":")
        startClient(url[0], url[1])
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
                pipeline.addLast(ContextUtil.getBean(RouterToServiceHandler::class.java))
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