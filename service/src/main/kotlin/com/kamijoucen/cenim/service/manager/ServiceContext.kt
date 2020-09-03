package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.common.manager.ICacheManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class ServiceContext {

    @Autowired
    @Qualifier("memoryCacheManager")
    lateinit var cacheManager: ICacheManager

    @Autowired
    lateinit var routerClientToServiceConnManager: RouterClientToServiceConnManager

    @Autowired
    lateinit var msgParseManager: ServiceMsgParseManager

}