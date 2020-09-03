package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.manager.AbstractMemoryConnManager
import com.kamijoucen.cenim.router.domain.RouterToServiceServerConn
import org.springframework.stereotype.Component

@Component
class RouterToServiceServerConnManager : AbstractMemoryConnManager<RouterToServiceServerConn>() {

}