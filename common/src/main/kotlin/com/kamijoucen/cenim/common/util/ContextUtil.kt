package com.kamijoucen.cenim.common.util

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ContextUtil : ApplicationContextAware {

    override fun setApplicationContext(context: ApplicationContext) {
        ContextUtil.context = context
    }

    companion object {
        private lateinit var context: ApplicationContext

        private val cache = ConcurrentHashMap<Any, Any>()

        @Suppress("UNCHECKED_CAST")
        fun <T> getBean(clazz: Class<T>): T {
            val cacheBean = cache[clazz]
            return if (cacheBean != null) {
                cacheBean as T
            } else {
                context.getBean(clazz).also {
                    cache[clazz] = it!!
                }
            }
        }
    }


}