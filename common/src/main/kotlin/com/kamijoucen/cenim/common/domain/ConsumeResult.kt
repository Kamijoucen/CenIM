package com.kamijoucen.cenim.common.domain

class ConsumeResult(val success: Boolean,
                    val data: Map<String, String> = HashMap<String, String>())