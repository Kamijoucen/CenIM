package com.kamijoucen.cenim.router

import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.router.handler.ClientToRouterHandler
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.MessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.MessageProtocolEncoder
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel

fun startRouterServer(config: RouterConfig): Boolean {

    val bossGroup = NioEventLoopGroup()
    val workGroup = NioEventLoopGroup()

    val bootstrap = ServerBootstrap()
            .group(bossGroup, workGroup)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(object : ChannelInitializer<NioSocketChannel>() {
                @Throws(Exception::class)
                override fun initChannel(channel: NioSocketChannel) {
                    val pipeline = channel.pipeline()
                    pipeline.addLast("frameDecode", MessageFrameDecoder())
                            .addLast("frameEncode", MessageFrameEncoder())
                            .addLast("protocolDecode", MessageProtocolDecoder())
                            .addLast("protocolEncode", MessageProtocolEncoder())
                            .addLast("messageHandler", ContextUtil.getBean(ClientToRouterHandler::class.java))
                }
            })

    bootstrap.bind(config.port).sync()
    return true
}