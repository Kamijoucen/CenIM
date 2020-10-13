package com.kamijoucen.cenim.web.domain

import javax.validation.constraints.NotNull

class WebUser {

    @NotNull(message = "id cannot be empty")
    var id: Long? = null

    @NotNull(message = "password cannot be empty")
    var password: String? = null

}