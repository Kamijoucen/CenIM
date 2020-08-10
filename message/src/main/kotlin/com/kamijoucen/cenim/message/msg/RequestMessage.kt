package com.kamijoucen.cenim.message.msg

class RequestMessage : Message<Operation> {

    constructor() : super(MessageHeader(), null)

    constructor(id: Long, fromId: Long, toId: Long, operation: Operation)
            : super(MessageHeader(1, 1, fromId, toId, id), operation)

    override fun getMessageBodyOperationClass(type: Int): Class<out Operation> {
        return RequestOperationType.fromType(type).opClass
    }


}