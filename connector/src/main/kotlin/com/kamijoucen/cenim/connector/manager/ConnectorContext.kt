package com.kamijoucen.cenim.connector.manager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConnectorContext {

    @Autowired
    lateinit var clientToConnectorConnManager: ClientToConnectorConnManager

    @Autowired
    lateinit var connectorToTransferServerConnManager: ConnectorToTransferServerConnManager

    @Autowired
    lateinit var connectorMsgParseManager: ConnectorMsgParseManager

}