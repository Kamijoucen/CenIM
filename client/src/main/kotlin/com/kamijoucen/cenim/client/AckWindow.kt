package com.kamijoucen.cenim.client

import com.kamijoucen.cenim.message.msg.Message
import io.netty.util.concurrent.DefaultPromise

class AckWindow : DefaultPromise<Message>() {
}