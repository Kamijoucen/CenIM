package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class ConnectorContext {

    @Autowired
    @Qualifier("memoryCacheManager")
    lateinit var cacheManager: ICacheManager

    @Autowired
    lateinit var clientToConnectorConnManager: ClientToConnectorConnManager

    @Autowired
    lateinit var connectorToTransferServerConnManager: ConnectorToTransferServerConnManager

    @Autowired
    lateinit var connectorMsgParseManager: ConnectorMsgParseManager

}