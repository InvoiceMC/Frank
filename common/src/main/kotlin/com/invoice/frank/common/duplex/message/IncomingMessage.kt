package com.invoice.frank.common.duplex.message

class IncomingMessage(
    private val raw: String
): Message {
    override val id: String = raw.substringBefore('|')
    override val content: String = raw.substringAfter('|')
    override val direction: MessageDirection = MessageDirection.INCOMING
}