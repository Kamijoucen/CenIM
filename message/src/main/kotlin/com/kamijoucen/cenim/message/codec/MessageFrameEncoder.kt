package com.kamijoucen.cenim.message.codec

import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender

class MessageFrameEncoder : LengthFieldPrepender(2)

