package com.kamijoucen.cenim.message.msg

class RequestMessage(header: MessageHeader, body: Operation)
    : Message<Operation>(header, body) {



}