package com.kamijoucen.cenim.message.codec

import io.netty.handler.codec.LengthFieldBasedFrameDecoder

class MessageFrameDecoder
    : LengthFieldBasedFrameDecoder(
        10240, 0, 2, 0, 2)

