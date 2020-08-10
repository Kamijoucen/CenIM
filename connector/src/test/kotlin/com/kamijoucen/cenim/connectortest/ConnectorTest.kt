package com.kamijoucen.cenim.connectortest

import com.kamijoucen.cenim.common.util.SpringUtil
import com.kamijoucen.cenim.connector.CenImApplication
import com.kamijoucen.cenim.connector.ConnectorConfig
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.MessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.MessageProtocolEncoder
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.string.StringOperation
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
@SpringBootTest(classes = [CenImApplication::class])
class ConnectorTest {


    @Test
    fun test() {
        var bean = SpringUtil.getBean(ConnectorConfig::class.java)
        println(bean.transfer)
    }

    @Test
    fun startClient() {
        val bootstrap = Bootstrap()
        bootstrap.channel(NioSocketChannel::class.java)

        val group = NioEventLoopGroup()
        bootstrap.group(group);

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

        val operation = StringOperation()
        operation.content = "lisicena"

        val requestMessage = RequestMessage(1, 11, 22, operation)

        channelFuture.channel().writeAndFlush(requestMessage)

        channelFuture.channel().closeFuture().sync()



    }

}