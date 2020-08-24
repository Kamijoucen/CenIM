package com.kamijoucen.cenim.message.msg

class MessageHeader(var version: Int, var type: Int,
                    var fromId: Long, var toId: Long, var msgId: Long)