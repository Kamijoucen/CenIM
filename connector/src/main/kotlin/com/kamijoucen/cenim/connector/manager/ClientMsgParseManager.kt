package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.message.msg.MessageBody
import com.kamijoucen.cenim.message.msg.RequestBodyType
import com.kamijoucen.cenim.message.msg.handler.ChCtx
import io.netty.channel.ChannelHandlerContext
import org.springframework.stereotype.Component

@Component
class ClientMsgParseManager {

    private val parseMap = HashMap<Class<out MessageBody>, IMConsumer<out MessageBody, ChCtx>>()

    final fun <T : MessageBody> register(clazz: Class<T>,
                                         consumer: IMConsumer<out MessageBody, ChCtx>) {
        parseMap[clazz] = consumer
    }


    init {
        RequestBodyType.values().forEach {
            register(it.opClass, it.consumer)
        }
    }


}