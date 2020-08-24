package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.message.msg.body.CustomMessageBody
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import com.kamijoucen.cenim.message.msg.body.StringMessageResult
import com.kamijoucen.cenim.message.msg.body.response.AckMessageBody
import com.kamijoucen.cenim.message.msg.body.response.AckMessageResult
import java.util.function.Predicate

enum class ResponseBodyType(val type: Int,
                            val opClass: Class<out MessageBody>,
                            val opResultClass: Class<out MessageResult>) {

    STRING_MSG(1, StringMessageBody::class.java, StringMessageResult::class.java),
    CUSTOM_MSG(2, CustomMessageBody::class.java, StringMessageResult::class.java),
    ACK_MSG(3, AckMessageBody::class.java, AckMessageResult::class.java),
    ;

    companion object {}

}

fun ResponseBodyType.Companion.fromType(type: Int): ResponseBodyType {
    return getMessageType(
            Predicate { opTypeResponse: ResponseBodyType -> opTypeResponse.type == type }
    )
}

fun ResponseBodyType.Companion.fromOperation(messageBody: MessageBody): ResponseBodyType {
    return getMessageType(
            Predicate { opTypeResponse: ResponseBodyType -> opTypeResponse.opClass == messageBody.javaClass }
    )
}

private fun ResponseBodyType.Companion.getMessageType(predicate: Predicate<ResponseBodyType>): ResponseBodyType {
    enumValues<ResponseBodyType>().forEach {
        if (predicate.test(it)) {
            return it
        }
    }
    return ResponseBodyType.STRING_MSG
}
