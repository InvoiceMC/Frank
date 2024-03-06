package com.invoice.frank.common.duplex

import com.invoice.frank.common.enums.MessageDirection

class OutgoingMessage(
    override val content: String
): Message {
    override val id: String = System.currentTimeMillis().toString()
    override val direction: MessageDirection = MessageDirection.OUTGOING

    fun process() = "$id|$content"
}