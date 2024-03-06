package com.invoice.frank.common.duplex.message

class IncomingMessage(
    override val id: String,
    override val data: Any
): Message {
    override val direction: MessageDirection = MessageDirection.INCOMING
}