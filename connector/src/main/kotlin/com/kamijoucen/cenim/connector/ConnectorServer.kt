package com.kamijoucen.cenim.connector

import com.kamijoucen.cenim.common.util.SpringUtil
import com.kamijoucen.cenim.connector.handler.MessageHandler
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.MessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.MessageProtocolEncoder
import com.kamijoucen.cenim.message.msg.MessageHeader
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel

fun startConnectorServer(config: ConnectorConfig): Boolean {

    val bossGroup: EventLoopGroup = NioEventLoopGroup()
    val workGroup: EventLoopGroup = NioEventLoopGroup()

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
                            .addLast("messageHandler", SpringUtil.getBean(MessageHandler::class.java))
                }
            })

    bootstrap.bind(config.port).sync()
    return true
}