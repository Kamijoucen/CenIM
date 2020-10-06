package com.kamijoucen.cenim.router.domain

import com.kamijoucen.cenim.common.domain.IMUser

class UserImpl(override val id: Long,
               override var name: String,
               override var status: Int) : IMUser {

}