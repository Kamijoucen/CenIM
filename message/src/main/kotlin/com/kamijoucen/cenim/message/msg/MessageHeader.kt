package com.kamijoucen.cenim.message.msg

class MessageHeader {

    constructor()

    constructor(version: Int, type: Int, fromId: Long, toId: Long, msgId: Long) {
        this.version = version
        this.type = type
        this.fromId = fromId
        this.toId = toId
        this.msgId = msgId
    }

    var version: Int = -1
    var type: Int = -1
    var fromId: Long = -1
    var toId: Long = -1
    var msgId: Long = -1
}