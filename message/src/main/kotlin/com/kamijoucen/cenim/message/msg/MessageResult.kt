package com.kamijoucen.cenim.message.msg

abstract class MessageResult {

    abstract fun getProperty(key: String = "content"): String

}