package com.kamijoucen.cenim.message.msg

abstract class Operation : MessageBody() {
    abstract fun execute(): OperationResult
}