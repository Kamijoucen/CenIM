package com.kamijoucen.cenim.connectortest

import com.kamijoucen.cenim.common.util.SpringUtil
import com.kamijoucen.cenim.connector.CenImApplication
import com.kamijoucen.cenim.connector.ConnectorConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [CenImApplication::class])
class ConnectorTest {


    @Test
    fun test() {
        var bean = SpringUtil.getBean(ConnectorConfig::class.java)
        println(bean.transfer)
    }

}