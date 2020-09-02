package com.kamijoucen.cenim.transfer.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class TransferContext {

    @Autowired
    @Qualifier("memoryCacheManager")
    lateinit var cacheManager: ICacheManager

    @Autowired
    lateinit var connectorClientToTransferConnManager: ConnectorClientToTransferConnManager

    @Autowired
    lateinit var msgParseManager: TransferMsgParseManager

}