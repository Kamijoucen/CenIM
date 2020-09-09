package com.kamijoucen.cenim.common.conn


class ConsumeResult(val success: Boolean,
                    val next: Boolean,
                    val data: Map<String, String> = HashMap<String, String>())