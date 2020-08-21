package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.manager.AbstractMemoryConnManager
import com.kamijoucen.cenim.connector.domain.ClientToConnectorConn
import org.springframework.stereotype.Component

@Component
class ClientConnContextManager : AbstractMemoryConnManager<ClientToConnectorConn>() {

}