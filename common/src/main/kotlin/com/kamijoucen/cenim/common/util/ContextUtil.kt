package com.kamijoucen.cenim.common.util

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class ContextUtil : ApplicationContextAware {

    override fun setApplicationContext(context: ApplicationContext) {
        ContextUtil.context = context
    }

    companion object {
        private lateinit var context: ApplicationContext

        fun <T> getBean(clazz: Class<T>): T {
            return context.getBean(clazz)
        }
    }


}