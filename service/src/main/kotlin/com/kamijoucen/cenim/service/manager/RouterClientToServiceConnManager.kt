package com.kamijoucen.cenim.service.manager

import com.kamijoucen.cenim.common.manager.AbstractMemoryConnManager
import com.kamijoucen.cenim.service.domain.RouterClientToServiceConn
import org.springframework.stereotype.Component

@Component
class RouterClientToServiceConnManager
    : AbstractMemoryConnManager<RouterClientToServiceConn>() {
}