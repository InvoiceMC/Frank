package com.invoice.frank.common.duplex

import com.invoice.frank.common.duplex.message.Message
import java.io.Serializable

interface Duplex {
    val messages: MutableList<Message>

    fun send(message: String)
    fun <K: Serializable> sendObject(obj: K)
}