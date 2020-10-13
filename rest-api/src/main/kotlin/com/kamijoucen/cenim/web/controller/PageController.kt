package com.kamijoucen.cenim.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("page")
class PageController {

    @RequestMapping("chat")
    fun chat(): String {
        return "chat"
    }

}