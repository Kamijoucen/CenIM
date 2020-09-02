package com.kamijoucen.cenim.transfer

import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.server.ServerMessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.server.ServerMessageProtocolEncoder
import com.kamijoucen.cenim.transfer.handler.ConnectorClientToTransferHandler
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel

fun startTransferServer(config: TransferConfig): Boolean {
    val bootstrap = ServerBootstrap()
            .group(NioEventLoopGroup(), NioEventLoopGroup())
            .channel(NioServerSocketChannel::class.java)
            .childHandler(object : ChannelInitializer<NioSocketChannel>() {
                @Throws(Exception::class)
                override fun initChannel(channel: NioSocketChannel) {
                    val pipeline = channel.pipeline()
                    pipeline.addLast("frameDecode", MessageFrameDecoder())
                            .addLast("frameEncode", MessageFrameEncoder())
                            .addLast("protocolDecode", ServerMessageProtocolDecoder())
                            .addLast("protocolEncode", ServerMessageProtocolEncoder())
                            .addLast("messageHandler", ContextUtil.getBean(ConnectorClientToTransferHandler::class.java))
                }
            })
    bootstrap.bind(config.port).sync()
    return true
}