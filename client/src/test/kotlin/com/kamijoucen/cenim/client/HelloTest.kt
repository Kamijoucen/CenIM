package com.kamijoucen.cenim.client

import com.kamijoucen.cenim.client.domain.IMClientBuilder
import com.kamijoucen.cenim.client.domain.IMHost
import org.junit.Test

class HelloTest {

    @Test
    fun test1() {
        val builder = IMClientBuilder(listOf(IMHost("127.0.0.1", "5238")))
        val client = builder.build()




    }

}
