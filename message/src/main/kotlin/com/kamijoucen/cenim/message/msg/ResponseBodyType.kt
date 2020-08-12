package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.message.msg.string.StringMessageBody
import com.kamijoucen.cenim.message.msg.string.StringMessageResult
import java.util.function.Predicate

enum class ResponseBodyType(val type: Int,
                           val opClass: Class<out MessageBody>,
                           val opResultClass: Class<out MessageResult>) {

    STRING_CHAT(1, StringMessageBody::class.java, StringMessageResult::class.java);

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
    return ResponseBodyType.STRING_CHAT
}
