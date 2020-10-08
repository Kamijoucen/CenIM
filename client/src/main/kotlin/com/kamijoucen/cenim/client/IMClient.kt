package com.kamijoucen.cenim.client

import com.kamijoucen.cenim.client.domain.AckWindow
import com.kamijoucen.cenim.client.domain.IMHost
import com.kamijoucen.cenim.client.domain.LoginParam
import com.kamijoucen.cenim.client.handler.ResponseDispatcherHandler
import com.kamijoucen.cenim.client.manager.AckWindowManager
import com.kamijoucen.cenim.client.manager.MsgProcessManager
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.MessageBody
import com.kamijoucen.cenim.message.msg.MessageBodyType
import com.kamijoucen.cenim.message.msg.body.ConnectMessageBody
import com.kamijoucen.cenim.message.msg.util.MessageFactory
import io.netty.channel.ChannelFactory
import io.netty.channel.ChannelFuture

class IMClient(private val param: LoginParam,
               private val host: IMHost,
               private val processes: MsgProcessManager) {

    private lateinit var channelFuture: ChannelFuture
    private val ackManager: AckWindowManager = AckWindowManager()
    private val msgFactory: MessageFactory = MessageFactory(param.userId)

    fun connect() {
        this.channelFuture = startClient(host, ResponseDispatcherHandler(ackManager, processes))
        val msg = sendMsg(-1, ConnectMessageBody(param.userId.toString())).get()
    }

    fun sendMsg(destId: Long, body: MessageBody): AckWindow {
        return sendMsg(msgFactory.getMsg(destId, body))
    }

    fun syncClose() {
        channelFuture.channel().closeFuture().sync()
    }

    private fun sendMsg(msg: Message): AckWindow {
        val ackWindow = AckWindow(msg)
        ackManager.add(msg.header.msgId, ackWindow)
        channelFuture.channel().writeAndFlush(msg)
        return ackWindow
    }

}