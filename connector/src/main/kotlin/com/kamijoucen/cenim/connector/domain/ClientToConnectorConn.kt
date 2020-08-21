package com.kamijoucen.cenim.connector.domain

import com.kamijoucen.cenim.common.domain.IMConn
import com.kamijoucen.cenim.connector.manager.ChCtx
import java.io.Serializable

class ClientToConnectorConn(private val ctx: ChCtx) : IMConn {

    override fun getId(): Serializable {
        TODO("Not yet implemented")
    }

    override fun getCtx() = ctx

    override fun close() {
        TODO("Not yet implemented")
    }
}