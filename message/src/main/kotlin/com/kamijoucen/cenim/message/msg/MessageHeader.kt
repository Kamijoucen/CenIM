package com.kamijoucen.cenim.message.msg

class MessageHeader(val version: Int, val type: Int,
                    val fromId: Long, val toId: Long, val msgId: Long)