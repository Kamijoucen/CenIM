package com.kamijoucen.cenim.message.msg

class MessageHeader(val version: Int, val bodyType: Int,
                    val fromId: Long, val destId: Long, val msgId: Long)