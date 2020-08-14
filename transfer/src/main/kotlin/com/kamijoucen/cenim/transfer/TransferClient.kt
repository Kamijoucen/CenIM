package com.kamijoucen.cenim.transfer

import io.netty.bootstrap.Bootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

fun startTransferClient(config: TransferConfig): Boolean {
    val businessUrl = config.business
    businessUrl.forEach {
        var url = it.split(":")

        val bootstrap = Bootstrap()
        bootstrap.channel(NioSocketChannel::class.java)

        val group = NioEventLoopGroup()

        bootstrap.group(group)







    }
    return false
}