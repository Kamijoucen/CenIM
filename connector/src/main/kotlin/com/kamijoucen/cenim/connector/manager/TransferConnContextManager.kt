package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.manager.AbstractMemoryConnManager
import com.kamijoucen.cenim.connector.conn.ClientToConnectorConn
import com.kamijoucen.cenim.connector.conn.ConnectorToTransferConn
import org.springframework.stereotype.Component

@Component
class TransferConnContextManager : AbstractMemoryConnManager<ConnectorToTransferConn>() {

}