package com.kamijoucen.cenim.message.msg

abstract class MessageResult {

    abstract fun getProperty(key: String): String

    abstract fun getContent() : String

}