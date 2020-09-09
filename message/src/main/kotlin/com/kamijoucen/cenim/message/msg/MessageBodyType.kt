package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.message.msg.body.CustomMessageBody
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import com.kamijoucen.cenim.message.msg.body.StringMessageResult
import com.kamijoucen.cenim.message.msg.body.OnlineMessageBody
import com.kamijoucen.cenim.message.msg.body.OnlineMessageResult
import com.kamijoucen.cenim.message.msg.body.AckMessageBody
import com.kamijoucen.cenim.message.msg.body.AckMessageResult

import java.util.function.Predicate

enum class MessageBodyType(val type: Int,
                           val bodyClass: Class<out MessageBody>,
                           val bodyResultClass: Class<out MessageResult>) {

    /**
     * @property STRING_MSG 字符串消息
     */
    STRING_MSG(1, StringMessageBody::class.java, StringMessageResult::class.java),
    /**
     * @property CUSTOM_MSG 自定义消息
     */
    CUSTOM_MSG(2, CustomMessageBody::class.java, StringMessageResult::class.java),
    /**
     * @property ONLINE_MSG 上线消息
     */
    ONLINE_MSG(3, OnlineMessageBody::class.java, OnlineMessageResult::class.java),
    /**
     * @property ACK_MSG ack消息
     */
    ACK_MSG(4, AckMessageBody::class.java, AckMessageResult::class.java),
    /**
     * @property INTERNAL_MSG 内部消息
     */
    INTERNAL_MSG(4, AckMessageBody::class.java, AckMessageResult::class.java),
    ;

    companion object {}

}

fun MessageBodyType.Companion.fromType(type: Int): MessageBodyType {
    return getMessageType { opTypeMessage: MessageBodyType -> opTypeMessage.type == type }
}

fun MessageBodyType.Companion.fromOperation(messageBody: MessageBody): MessageBodyType {
    return getMessageType { opTypeMessage: MessageBodyType -> opTypeMessage.bodyClass == messageBody.javaClass }
}

private fun MessageBodyType.Companion.getMessageType(predicate: Predicate<MessageBodyType>): MessageBodyType {
    enumValues<MessageBodyType>().forEach {
        if (predicate.test(it)) {
            return it
        }
    }
    return MessageBodyType.STRING_MSG
}
