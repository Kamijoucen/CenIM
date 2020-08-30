package com.kamijoucen.cenim.transfer.manager

import com.kamijoucen.cenim.common.manager.AbstractMemoryConnManager
import com.kamijoucen.cenim.transfer.domain.ConnectorClientToTransferConn
import org.springframework.stereotype.Component

@Component
class ConnectorClientToTransferConnManager
    : AbstractMemoryConnManager<ConnectorClientToTransferConn>() {
}