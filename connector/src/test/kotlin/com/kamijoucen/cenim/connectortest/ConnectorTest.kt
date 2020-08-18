package com.kamijoucen.cenim.connectortest

import com.kamijoucen.cenim.common.util.ContextUtil
import com.kamijoucen.cenim.connector.CenImConnectorApp
import com.kamijoucen.cenim.connector.ConnectorConfig
import com.kamijoucen.cenim.message.codec.MessageFrameDecoder
import com.kamijoucen.cenim.message.codec.MessageFrameEncoder
import com.kamijoucen.cenim.message.codec.client.ClientMessageProtocolDecoder
import com.kamijoucen.cenim.message.codec.client.ClientMessageProtocolEncoder
import com.kamijoucen.cenim.message.msg.MessageHeader
import com.kamijoucen.cenim.message.msg.RequestMessage
import com.kamijoucen.cenim.message.msg.RequestBodyType
import com.kamijoucen.cenim.message.msg.custom.CustomMessageBody
import com.kamijoucen.cenim.message.msg.string.StringMessageBody
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
@SpringBootTest(classes = [CenImConnectorApp::class])
class ConnectorTest {


    @Test
    fun test() {
        var bean = ContextUtil.getBean(ConnectorConfig::class.java)
        println(bean.transfer)
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
                pipeline.addLast(ClientMessageProtocolDecoder())
                pipeline.addLast(ClientMessageProtocolEncoder())
            }
        })
        val channelFuture: ChannelFuture = bootstrap.connect("127.0.0.1", 5238)

        channelFuture.sync()

        val operation = StringMessageBody("lisicena")
        val header = MessageHeader(1, RequestBodyType.STRING_MSG.type, 11, 22, 33)
        val requestMessage = RequestMessage(header, operation)
        channelFuture.channel().writeAndFlush(requestMessage)


        val header2 = MessageHeader(1, RequestBodyType.CUSTOM_MSG.type, 11, 22, 33)
        val cusBody = CustomMessageBody().also {
            it.addParam("lg", "贾静")
            it.addParam("lp", "李思岑")
            it.addParam("test", "{\"lp\":\"李思岑\",\"lg\":\"贾静\"}")
        }
        val requestMessage2 = RequestMessage(header2, cusBody)
        channelFuture.channel().writeAndFlush(requestMessage2)

        channelFuture.channel().closeFuture().sync()



    }

}