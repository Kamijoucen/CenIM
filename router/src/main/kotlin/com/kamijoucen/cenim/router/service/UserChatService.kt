package com.kamijoucen.cenim.router.service

import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.domain.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserChatService {

    @Autowired
    private lateinit var msgService: MsgService

    /**
     * 发送消息
     * @param dest 发送目标
     * @param msg 待发送的消息
     */
    fun senMsg(dest: UserImpl, msg: Message) {

    }

    /**
     * @param dest 带接收的目标
     * @param msg 待接收的消息
     */
    fun receiveMsg(dest: UserImpl, msg: Message) {
    }

}