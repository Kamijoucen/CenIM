package com.kamijoucen.cenim.client.domain

import com.kamijoucen.cenim.message.msg.Message
import io.netty.util.concurrent.DefaultPromise

class AckWindow(val srcMsg: Message) : DefaultPromise<Message>()