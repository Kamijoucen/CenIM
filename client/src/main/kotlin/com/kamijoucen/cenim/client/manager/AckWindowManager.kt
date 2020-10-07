package com.kamijoucen.cenim.client.manager

import com.kamijoucen.cenim.client.AckWindow
import com.kamijoucen.cenim.message.msg.Message
import java.util.concurrent.ConcurrentHashMap

class AckWindowManager {

    private val map = ConcurrentHashMap<Long, AckWindow>()

    fun add(msgId: Long, msg: AckWindow) {
        map[msgId] = msg
    }

    fun set(msgId: Long, msg: Message) {
        val future = map[msgId]
        if (future != null) {
            future.setSuccess(msg)
            map.remove(msgId)
        }
    }

}