package com.kamijoucen.cenim.connectortest

import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.router.CenImRouterApp
import com.kamijoucen.cenim.router.RouterConfig
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.MessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.MessageProtocolEncoder
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.MessageHeader
import com.kamijoucen.cenim.message.msg.MessageBodyType
import com.kamijoucen.cenim.message.msg.body.CustomMessageBody
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [CenImRouterApp::class])
class RouterTest {


    @Test
    fun test() {
        var bean = ContextUtil.getBean(RouterConfig::class.java)
        println(bean.service)
    }

    @Test
    fun startClient() {
        val bootstrap = Bootstrap()
        bootstrap.channel(NioSocketChannel::class.java)

        val group = NioEventLoopGroup()
        bootstrap.group(group)

        bootstrap.handler(object : ChannelInitializer<NioSocketChannel?>() {
            @Throws(Exception::class)
            override fun initChannel(ch: NioSocketChannel?) {
                if (ch == null) {
                    return
                }
                val pipeline: ChannelPipeline = ch.pipeline()
                pipeline.addLast(MessageFrameDecoder())
                pipeline.addLast(MessageFrameEncoder())
                pipeline.addLast(MessageProtocolDecoder())
                pipeline.addLast(MessageProtocolEncoder())
            }
        })
        val channelFuture: ChannelFuture = bootstrap.connect("127.0.0.1", 5238)

        channelFuture.sync()

        val operation = StringMessageBody("lisicena")
        val header = MessageHeader(1, MessageBodyType.STRING_MSG.type, 11, 22, 33)
        val requestMessage = Message(header, operation)
        channelFuture.channel().writeAndFlush(requestMessage)


        val header2 = MessageHeader(1, MessageBodyType.CUSTOM_MSG.type, 11, 22, 44)
        val cusBody = CustomMessageBody().also {
            it.addParam("lg", "贾静")
            it.addParam("lp", "李思岑")
            it.addParam("test", "{\"lp\":\"李思岑\",\"lg\":\"贾静\"}")
        }
        val requestMessage2 = Message(header2, cusBody)
        channelFuture.channel().writeAndFlush(requestMessage2)

        channelFuture.channel().closeFuture().sync()

    }

}