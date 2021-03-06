package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class ServiceContext {

    @Autowired
    @Qualifier("redisCacheManager")
    lateinit var cacheManager: ICacheManager

    @Autowired
    lateinit var routerClientToServiceConnManager: RouterClientToServiceConnManager

    @Autowired
    lateinit var msgProcessManager: ServiceMsgProcessManager

    @Autowired
    lateinit var connectFutureManager: ConnectFutureManager

}