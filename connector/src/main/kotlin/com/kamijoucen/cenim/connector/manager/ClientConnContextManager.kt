package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConn
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class ClientConnContextManager {

    private val ctxMap = HashMap<Serializable, IMConn>()

    fun put(conn: IMConn) = ctxMap.put(conn.getId(), conn)!!

    fun get(id: Serializable) = ctxMap[id]


}