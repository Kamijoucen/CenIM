package com.kamijoucen.cenim.client.domain

import com.kamijoucen.cenim.client.handler.ResponseDispatcherHandler
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.MessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.MessageProtocolEncoder
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

fun startClient(host: IMHost, handler: ResponseDispatcherHandler): ChannelFuture {
    val bootstrap = Bootstrap()
    bootstrap.channel(NioSocketChannel::class.java)

    val group = NioEventLoopGroup()
    bootstrap.group(group)

    bootstrap.handler(object : ChannelInitializer<NioSocketChannel>() {
        @Throws(Exception::class)
        override fun initChannel(ch: NioSocketChannel) {
            val pipeline: ChannelPipeline = ch.pipeline()
            pipeline.addLast(MessageFrameDecoder())
            pipeline.addLast(MessageFrameEncoder())
            pipeline.addLast(MessageProtocolDecoder())
            pipeline.addLast(MessageProtocolEncoder())
            pipeline.addLast(handler)
        }
    })
    val channelFuture = bootstrap.connect(host.host, host.port.toInt()).sync()
    return channelFuture
}
