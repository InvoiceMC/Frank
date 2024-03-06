package com.invoice.frank.common.duplex

import com.invoice.frank.common.duplex.message.Message

interface Duplex {
    val messages: MutableList<Message>

    fun send(message: String)
}