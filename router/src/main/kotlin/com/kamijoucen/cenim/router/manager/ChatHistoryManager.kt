package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.exception.HistoryNotFoundException
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class ChatHistoryManager {

    private val allChatHistory: ConcurrentHashMap<Long, LinkedList<Message>> = ConcurrentHashMap<Long, LinkedList<Message>>()

    fun init(userId: Long) {
        allChatHistory[userId] = LinkedList<Message>()
    }

    fun clear(userId: Long) = allChatHistory.remove(userId)


    fun setHistory(userId: Long, msg: Message) =
            allChatHistory[userId]?.also {
                it.add(msg)
            } ?: throw HistoryNotFoundException("history list not found")


    fun getHistory(userId: Long) = allChatHistory[userId]!!

}