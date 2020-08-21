package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConn
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class ClientConnContextManager {

    private val connMap = HashMap<Serializable, IMConn>()

    fun put(conn: IMConn) = connMap.put(conn.getId(), conn)!!

    fun get(id: Serializable) = connMap[id]

    fun remove(id: Serializable) = connMap.remove(id)!!


}