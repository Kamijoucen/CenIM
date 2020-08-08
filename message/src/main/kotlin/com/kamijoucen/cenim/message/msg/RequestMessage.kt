package com.kamijoucen.cenim.message.msg

class RequestMessage(header: MessageHeader, body: MessageBody)
    : Message<Operation>(header, body) {
}