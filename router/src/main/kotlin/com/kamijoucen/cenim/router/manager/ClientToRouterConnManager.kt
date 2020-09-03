package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.manager.AbstractMemoryConnManager
import com.kamijoucen.cenim.router.domain.ClientToRouterConn
import org.springframework.stereotype.Component

@Component
class ClientToRouterConnManager : AbstractMemoryConnManager<ClientToRouterConn>() {

}