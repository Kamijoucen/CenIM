package com.kamijoucen.cenim.message.msg

abstract class MessageBody {
    abstract fun execute(): MessageResult
}