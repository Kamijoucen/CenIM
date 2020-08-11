package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.message.msg.string.StringMessageBody
import com.kamijoucen.cenim.message.msg.string.StringMessageResult
import java.util.function.Predicate

enum class RequestMessageBodyType(val type: Int,
                                  val opClass: Class<out MessageBody>,
                                  val opResultClass: Class<out MessageResult>) {

    STRING_CHAT(1, StringMessageBody::class.java, StringMessageResult::class.java);

    companion object {
        fun fromType(type: Int): RequestMessageBodyType {
            return getMessageType(
                    Predicate { opTypeRequest: RequestMessageBodyType -> opTypeRequest.type == type }
            )
        }

        fun fromOperation(messageBody: MessageBody): RequestMessageBodyType {
            return getMessageType(
                    Predicate { opTypeRequest: RequestMessageBodyType -> opTypeRequest.opClass == messageBody.javaClass }
            )
        }

        private fun getMessageType(predicate: Predicate<RequestMessageBodyType>): RequestMessageBodyType {
            enumValues<RequestMessageBodyType>().forEach {
                if (predicate.test(it)) {
                    return it
                }
            }
            return RequestMessageBodyType.STRING_CHAT
        }

    }

}