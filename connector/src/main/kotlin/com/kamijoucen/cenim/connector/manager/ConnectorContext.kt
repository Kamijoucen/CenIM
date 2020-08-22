package com.kamijoucen.cenim.connector.manager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConnectorContext {

    @Autowired
    lateinit var clientConnContextManager: ClientConnContextManager

    @Autowired
    lateinit var clientMsgParseManager: ClientMsgParseManager

    @Autowired
    lateinit var transferConnContextManager: TransferConnContextManager

}