package com.kamijoucen.cenim.connector

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel

fun start(config: IMServerConfig): Boolean {

    val bossGroup: EventLoopGroup = NioEventLoopGroup()
    val workGroup: EventLoopGroup = NioEventLoopGroup()

    val bootstrap = ServerBootstrap()
            .group(bossGroup, workGroup)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(object : ChannelInitializer<SocketChannel>() {
                @Throws(Exception::class)
                override fun initChannel(channel: SocketChannel) {
                    val pipeline = channel.pipeline()

                }
            })

    bootstrap.bind(config.port).sync()
    return true
}