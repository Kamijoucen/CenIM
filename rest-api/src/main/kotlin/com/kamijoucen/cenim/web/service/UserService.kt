package com.kamijoucen.cenim.web.service

import com.kamijoucen.cenim.client.IMClient
import com.kamijoucen.cenim.client.domain.IMHost
import com.kamijoucen.cenim.client.domain.LoginParam
import com.kamijoucen.cenim.client.manager.MsgProcessManager
import com.kamijoucen.cenim.web.domain.WebUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var clientService: ClientService

    fun connect(user: WebUser): String {
        val imClient = IMClient(LoginParam(user.id!!),
                IMHost("127.0.0.1", "5238"),
                MsgProcessManager())
        val ack = imClient.connect()
        clientService.set(user.id!!, imClient)
        return ack.msg
    }

}