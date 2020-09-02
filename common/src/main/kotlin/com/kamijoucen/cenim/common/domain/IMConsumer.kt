package com.kamijoucen.cenim.common.domain

fun interface IMConsumer<M, C> {
    fun accept(msg: M, ctx: C): ConsumeResult
}