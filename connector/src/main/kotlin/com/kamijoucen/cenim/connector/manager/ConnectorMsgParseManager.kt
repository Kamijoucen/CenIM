package com.kamijoucen.cenim.connector.manager

import com.kamijoucen.cenim.common.domain.IMConsumer
import com.kamijoucen.cenim.common.util.ChCtx
import com.kamijoucen.cenim.message.msg.*
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class ConnectorMsgParseManager {

    private val log = LogFactory.getLog("ConnectorMsgParseManager")

    private val requestParseMap = ConcurrentHashMap<Int, IMConsumer<in RequestMessage, ChCtx>>()

    private val responseParseMap = ConcurrentHashMap<Int, IMConsumer<in ResponseMessage, ChCtx>>()

    init {
        RequestBodyType.values().forEach {
            when (it.type) {
                RequestBodyType.STRING_MSG.type ->
                    requestParseMap[it.type] = ReqParseFac.stringMsg()
                RequestBodyType.CUSTOM_MSG.type ->
                    requestParseMap[it.type] = ReqParseFac.customMsg()
                RequestBodyType.ONLINE_MSG.type ->
                    requestParseMap[it.type] = ReqParseFac.onlineMsg()
                else ->
                    log.warn("request msg parse not impl! type:${it.type}")
            }
        }

        ResponseBodyType.values().forEach {
            when (it.type) {
                ResponseBodyType.STRING_MSG.type ->
                    responseParseMap[it.type] = ResParseFac.stringMsg()
                ResponseBodyType.CUSTOM_MSG.type ->
                    responseParseMap[it.type] = ResParseFac.customMsg()
                ResponseBodyType.ACK_MSG.type ->
                    responseParseMap[it.type] = ResParseFac.ackMsg()
                else ->
                    log.warn("response msg parse not impl! type:${it.type}")
            }
        }
    }

    fun getRequestParse(type: Int) = requestParseMap[type]!!

    fun getResponseParse(type: Int) = responseParseMap[type]!!

}