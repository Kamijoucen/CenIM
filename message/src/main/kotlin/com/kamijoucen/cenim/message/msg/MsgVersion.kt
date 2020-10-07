package com.kamijoucen.cenim.message.msg


enum class MsgVersion(val version: Int) {

    V1(1);

    companion object {
        operator fun get(version: Int): MsgVersion {
            return values().first { it.version == version }
        }
    }
}