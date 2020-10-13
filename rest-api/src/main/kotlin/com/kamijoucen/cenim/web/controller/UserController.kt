package com.kamijoucen.cenim.web.controller

import com.kamijoucen.cenim.web.data.ApiResult
import com.kamijoucen.cenim.web.domain.WebUser
import com.kamijoucen.cenim.web.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @ResponseBody
    @RequestMapping("connect")
    fun connect(@Validated user: WebUser, bindResult: BindingResult): ApiResult {
        if (bindResult.hasErrors()) {
            return ApiResult(false, bindResult.allErrors.first().defaultMessage ?: "")
        }
        val msg = userService.connect(user)
        return ApiResult(true, msg)
    }

}