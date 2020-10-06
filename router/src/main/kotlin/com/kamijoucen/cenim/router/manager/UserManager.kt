package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.router.domain.UserImpl
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class UserManager {

    private val loginUser = ConcurrentHashMap<Long, UserImpl>()

    fun put(user: UserImpl) {
        loginUser[user.id] = user
    }

    fun get(id: Long) = loginUser[id]

    fun remove(id: Long) = loginUser.remove(id)

}