package com.kamijoucen.cenim.client

import com.kamijoucen.cenim.client.domain.IMHost
import com.kamijoucen.cenim.client.domain.LoginParam
import com.kamijoucen.cenim.client.domain.MsgProcess
import com.kamijoucen.cenim.client.manager.MsgProcessManager
import com.kamijoucen.cenim.common.util.JsonUtil
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.message.msg.MessageBodyType
import com.kamijoucen.cenim.message.msg.body.StringMessageBody
import org.junit.Test
import kotlin.contracts.contract

class HelloTest {

    fun getProcesses(fname: String): MsgProcessManager {
        val msgProcessManager = MsgProcessManager()

        msgProcessManager.register(MessageBodyType.STRING_MSG) {
            println("我是-----\t${fname}")
            println("============================ " + JsonUtil.toJson(it))
        }

        return msgProcessManager
    }

    @Test
    fun test1() {

        val loginParam = LoginParam(11)
        val host = IMHost("127.0.0.1", "5238")

        val client = IMClient(loginParam, host, getProcesses("11"))
        client.connect()
        client.sendMsg(22, StringMessageBody("李思岑测试"))
        client.syncClose()
    }

    @Test
    fun test2() {

        val loginParam = LoginParam(22)
        val host = IMHost("127.0.0.1", "5238")

        val client = IMClient(loginParam, host, getProcesses("22"))
        client.connect()
        client.syncClose()
    }

}
