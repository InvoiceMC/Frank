package com.invoice.frank.common.duplex.message

class OutgoingMessage(
    override val content: String
): Message {
    override val id: String = System.currentTimeMillis().toString()
    override val direction: MessageDirection = MessageDirection.OUTGOING

    fun process() = "$id|$content"
}