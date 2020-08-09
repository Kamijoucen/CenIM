package com.kamijoucen.cenim.message.msg

import com.kamijoucen.cenim.message.msg.string.StringOperation
import com.kamijoucen.cenim.message.msg.string.StringOperationResult
import java.util.function.Predicate

enum class RequestOperationType(val type: Int,
                                val opClass: Class<out Operation>,
                                val opResultClass: Class<out OperationResult>) {

    STRING_CHAT(1, StringOperation::class.java, StringOperationResult::class.java);

    companion object {
        fun fromType(type: Int): RequestOperationType {
            return getRequestOperationType(
                    Predicate { opType: RequestOperationType -> opType.type == type }
            )
        }

        fun fromOperation(operation: Operation): RequestOperationType {
            return getRequestOperationType(
                    Predicate { opType: RequestOperationType -> opType.opClass == operation.javaClass }
            )
        }

        private fun getRequestOperationType(predicate: Predicate<RequestOperationType>): RequestOperationType {
            enumValues<RequestOperationType>().forEach {
                if (predicate.test(it)) {
                    return it
                }
            }
            return RequestOperationType.STRING_CHAT
        }

    }

}