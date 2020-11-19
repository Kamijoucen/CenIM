package com.kamijoucen.cenim.common.util.idgen

/**
 * 生成的ID二进制结构如下
 * 0 00000000000000000000000000000000000000000 00000 00000 000000000000
 * 第一段符号位不用
 * 第二段时间戳，长度41位
 * 第三段消息类型，长度5位
 * 第四段接入层ID，长度5位
 * 第五段序列号，长度12位
 */
class SnowflakeMsgIdGenerator(workerId: Long) {

    private val twepoch = 1420041600000L

    private val workerIdBits = 5L

    private val msgTypeBits = 5L

    private val maxWorkerId = (-1L shl workerIdBits.toInt()).inv()

    private val sequenceBits = 12L

    private val workerIdShift = sequenceBits

    private val msgTypeShift = sequenceBits + workerIdBits

    private val timestampLeftShift = sequenceBits + workerIdBits + msgTypeBits

    private val sequenceMask = (-1L shl sequenceBits.toInt()).inv()

    private val workerId: Long

    private var sequence = 0L

    private var lastTimestamp = -1L

    @Synchronized
    fun nextId(msgType: Long): Long {
        var timestamp = timeGen()
        if (timestamp < lastTimestamp) {
            throw RuntimeException(String.format("Clock moved backwards.  " +
                    "Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp))
        }
        if (lastTimestamp == timestamp) {
            sequence = sequence + 1 and sequenceMask
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp)
            }
        } else {
            sequence = 0L
        }
        lastTimestamp = timestamp
        return (timestamp - twepoch shl timestampLeftShift.toInt()
                or (msgType shl msgTypeShift.toInt())
                or (workerId shl workerIdShift.toInt())
                or sequence)
    }

    private fun tilNextMillis(lastTimestamp: Long): Long {
        var timestamp = timeGen()
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen()
        }
        return timestamp
    }

    private fun timeGen(): Long {
        return System.currentTimeMillis()
    }

    init {
        require(!(workerId > maxWorkerId || workerId < 0)) {
            String.format("worker Id can't be greater than %d or less than 0",
                    maxWorkerId)
        }
        this.workerId = workerId
    }
}