package com.kamijoucen.cenim.router.domain

import com.kamijoucen.cenim.common.domain.IMUser
import com.kamijoucen.cenim.router.util.UserConnectFuture


class UserImpl(override val id: Long,
               override var name: String,
               override var status: Int) : IMUser {

    private val connectFuture = UserConnectFuture()

    fun isConnect(): Boolean {
        return connectFuture.get() == true
    }

    fun connect() = connectFuture.setSuccess(true)!!

}