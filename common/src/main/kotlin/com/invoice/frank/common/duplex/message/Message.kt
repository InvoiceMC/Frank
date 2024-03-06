package com.invoice.frank.common.duplex.message

interface Message {
    val id: String
    val content: String
    val direction: MessageDirection
}