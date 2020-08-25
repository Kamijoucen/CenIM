package com.kamijoucen.cenim.common.util

import kotlin.random.Random


/**
 * todo 临时实现，后期改为SnowFlake算法
 */
object IdGenerator {

    fun nextId() = Random.Default.nextLong()

}