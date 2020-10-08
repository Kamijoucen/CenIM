package com.kamijoucen.cenim.client

import com.kamijoucen.cenim.client.domain.IMHost
import com.kamijoucen.cenim.client.domain.LoginParam
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import org.junit.Test
import kotlin.contracts.contract

class HelloTest {

    @Test
    fun test1() {

        val loginParam = LoginParam(11)
        val host = IMHost("127.0.0.1", "5238")

        val client = IMClient(loginParam, host)
        client.connect()

        client.sendMsg(11, StringMessageBody("李思岑测试"))
    }

}
