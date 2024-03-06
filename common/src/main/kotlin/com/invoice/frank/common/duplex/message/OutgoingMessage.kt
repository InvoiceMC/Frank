package com.invoice.frank.common.duplex.message

class OutgoingMessage(
    override var data: Any
): Message {
    override val id: String = System.currentTimeMillis().toString()
    override val direction: MessageDirection = MessageDirection.OUTGOING

    fun toIncomingMessage(): IncomingMessage {
        return IncomingMessage(this.id, data)
    }
}