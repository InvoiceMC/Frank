package com.invoice.frank.common.duplex

import com.invoice.frank.common.enums.MessageDirection

interface Message {
    val id: String
    val content: String
    val direction: MessageDirection
}