package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class RouterContext {

    @Autowired
    @Qualifier("memoryCacheManager")
    lateinit var cacheManager: ICacheManager

    @Autowired
    lateinit var clientToRouterConnManager: ClientToRouterConnManager

    @Autowired
    lateinit var routerToServiceServerConnManager: RouterToServiceServerConnManager

    @Autowired
    lateinit var routerMsgProcessManager: RouterMsgProcessManager

    @Autowired
    lateinit var chatHistoryManager: ChatHistoryManager

}