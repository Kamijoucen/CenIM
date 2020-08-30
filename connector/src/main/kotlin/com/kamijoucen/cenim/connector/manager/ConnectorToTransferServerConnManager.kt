package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.manager.AbstractMemoryConnManager
import com.kamijoucen.cenim.connector.domain.ConnectorToTransferServerConn
import org.springframework.stereotype.Component

@Component
class ConnectorToTransferServerConnManager : AbstractMemoryConnManager<ConnectorToTransferServerConn>() {

}