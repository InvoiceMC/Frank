package com.invoice.frank.common.duplex.message

import java.util.UUID

class OutgoingMessage(
    override var data: Any
): Message {
    override val id: UUID = UUID.randomUUID()
    override val direction: MessageDirection = MessageDirection.OUTGOING

    fun toIncomingMessage(): IncomingMessage {
        return IncomingMessage(this.id, data)
    }
}