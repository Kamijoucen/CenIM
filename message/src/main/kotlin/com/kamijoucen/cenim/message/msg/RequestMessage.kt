package com.kamijoucen.cenim.message.msg

class RequestMessage : Message<Operation>() {


    override fun getMessageBodyOperationClass(type: Int): Class<out Operation> {
        return RequestOperationType.fromType(type).opClass
    }


}