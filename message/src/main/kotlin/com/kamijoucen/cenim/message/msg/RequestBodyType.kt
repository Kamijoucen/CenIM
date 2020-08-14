package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.message.msg.custom.CustomMessageBody
import com.kamijoucen.cenim.message.msg.string.StringMessageBody
import com.kamijoucen.cenim.message.msg.string.StringMessageResult
import java.util.function.Predicate

enum class RequestBodyType(val type: Int,
                           val opClass: Class<out MessageBody>,
                           val opResultClass: Class<out MessageResult>) {

    STRING_MSG(1, StringMessageBody::class.java, StringMessageResult::class.java),
    CUSTOM_MSG(2, CustomMessageBody::class.java, StringMessageResult::class.java);

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
