package com.kamijoucen.cenim.transfer.manager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransferContext {

    @Autowired
    lateinit var connectorClientToTransferConnManager: ConnectorClientToTransferConnManager


}