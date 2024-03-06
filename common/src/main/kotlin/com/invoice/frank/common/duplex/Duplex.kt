package com.invoice.frank.common.duplex

interface Duplex {
    val stringInIdentifier: String
    val stringOutIdentifier: String
    val messages: MutableList<Message>

    fun send(message: String)
}