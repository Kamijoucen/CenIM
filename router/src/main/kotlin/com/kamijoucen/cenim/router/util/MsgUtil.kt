package com.kamijoucen.cenim.router.util

import com.kamijoucen.cenim.common.conn.IMConn
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.common.util.MappingKeyGenerator
import com.kamijoucen.cenim.message.msg.Message
import com.kamijoucen.cenim.router.manager.RouterContext
import com.kamijoucen.cenim.router.domain.RouterToServiceServerConn
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

object MsgUtil {

    private val log = LogFactory.getLog("RouterMsgUtil")

    fun sendMsg(msg: Message, conn: IMConn) {
        if (conn.getCtx().channel().isOpen) {
            conn.getCtx().writeAndFlush(msg)
        } else {
            TODO()
        }
    }

    fun sendMsg(msg: Message, ctx: ChCtx) {
        if (ctx.channel().isOpen) {
            ctx.channel().writeAndFlush(msg)
        }
    }

    fun nextMsgId(msg: Message, conn: IMConn) {
    }

}