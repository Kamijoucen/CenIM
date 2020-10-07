package com.kamijoucen.cenim.client

import com.kamijoucen.cenim.client.domain.IMHost
import com.kamijoucen.cenim.client.manager.AckWindowManager
import com.kamijoucen.cenim.message.msg.MessageBodyType
import com.kamijoucen.cenim.message.msg.util.MessageFactory
import io.netty.channel.ChannelFactory
import io.netty.channel.ChannelFuture

class IMClient(private val userId: Long,
               private val host: IMHost,
               private val channel: ChannelFuture,
               private val ackManager: AckWindowManager,
               private val msgFactory: MessageFactory = MessageFactory(userId)) {

}