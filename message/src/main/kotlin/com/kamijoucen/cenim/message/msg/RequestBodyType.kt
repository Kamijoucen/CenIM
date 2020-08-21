package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.message.msg.body.CustomMessageBody
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import com.kamijoucen.cenim.message.msg.body.StringMessageResult
import com.kamijoucen.cenim.message.msg.handler.MessageHandlerFactory
import com.kamijoucen.cenim.message.msg.handler.customMsg
import com.kamijoucen.cenim.message.msg.handler.stringMsg
import io.netty.channel.ChannelHandlerContext
import java.util.function.Predicate

enum class RequestBodyType(val type: Int,
                           val opClass: Class<out MessageBody>,
                           val opResultClass: Class<out MessageResult>,
                           val consumer: IMConsumer<out MessageBody, ChannelHandlerContext>) {

    STRING_MSG(1, StringMessageBody::class.java, StringMessageResult::class.java, MessageHandlerFactory.stringMsg()),
    CUSTOM_MSG(2, CustomMessageBody::class.java, StringMessageResult::class.java, MessageHandlerFactory.customMsg()),
//    LOGIN_MSG(3, )
    ;

    companion object {}

}

fun RequestBodyType.Companion.fromType(type: Int): RequestBodyType {
    return getMessageType(
            Predicate { opTypeRequest: RequestBodyType -> opTypeRequest.type == type }
    )
}

fun RequestBodyType.Companion.fromOperation(messageBody: MessageBody): RequestBodyType {
    return getMessageType(
            Predicate { opTypeRequest: RequestBodyType -> opTypeRequest.opClass == messageBody.javaClass }
    )
}

private fun RequestBodyType.Companion.getMessageType(predicate: Predicate<RequestBodyType>): RequestBodyType {
    enumValues<RequestBodyType>().forEach {
        if (predicate.test(it)) {
            return it
        }
    }
    return RequestBodyType.STRING_MSG
}
