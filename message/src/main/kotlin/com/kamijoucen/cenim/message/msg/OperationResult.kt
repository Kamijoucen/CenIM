package com.kamijoucen.cenim.message.msg

abstract class OperationResult {

    abstract fun getProperty(key: String = "content"): String

}