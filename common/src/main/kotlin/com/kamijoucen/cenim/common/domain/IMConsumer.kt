package com.kamijoucen.cenim.common.domain

interface IMConsumer<M, C> {
    fun accept(msg: M, ctx: C)
}