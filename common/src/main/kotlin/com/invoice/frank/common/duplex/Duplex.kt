package com.invoice.frank.common.duplex

import com.invoice.frank.common.duplex.message.Message

interface Duplex {
    val stringInIdentifier: String
    val stringOutIdentifier: String
    val messages: MutableList<Message>

    fun send(message: String)
}