package com.kamijoucen.cenim.router.manager

import com.kamijoucen.cenim.common.util.idgen.SnowflakeMsgIdGenerator
import org.springframework.stereotype.Component

@Component
class IdGeneratorManager {

    // TODO: 2020/11/20 这里的workerId在zookeeper中注册后生成
    val msgIdGenerator = SnowflakeMsgIdGenerator(1)


}