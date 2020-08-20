package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.message.msg.MessageBody
import com.kamijoucen.cenim.message.msg.RequestBodyType
import io.netty.channel.ChannelHandlerContext
import org.springframework.stereotype.Component

@Component
class ClientMsgParseManager {

    private val parseMap = HashMap<Class<out MessageBody>, IMConsumer<MessageBody, ChannelHandlerContext>>()


    final fun <T : MessageBody> register(clazz: Class<T>,
                                         consumer: IMConsumer<MessageBody, ChannelHandlerContext>) {
        parseMap[clazz] = consumer
    }

    init {
        this.register(RequestBodyType.STRING_MSG.opClass, object : IMConsumer<MessageBody, ChannelHandlerContext> {
            override fun accept(msg: MessageBody, ctx: ChannelHandlerContext) {

            }
        })
    }


}