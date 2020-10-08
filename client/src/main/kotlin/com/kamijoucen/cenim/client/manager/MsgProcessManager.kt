package com.kamijoucen.cenim.client.manager

import com.kamijoucen.cenim.client.domain.MsgProcess
import com.kamijoucen.cenim.message.msg.MessageBodyType
import com.kamijoucen.cenim.message.msg.fromType

class MsgProcessManager(private val processes: HashMap<Int, MsgProcess> = HashMap()) {

    fun register(type: MessageBodyType, process: MsgProcess) {
        processes[type.type] = process
    }

    fun getProcess(type: Int): MsgProcess? {
        return processes[MessageBodyType.fromType(type).type]
    }

}