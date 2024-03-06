package com.invoice.frank.common.duplex.message

class OutgoingMessage(
    override var data: Any
): Message {
    override val id: String = System.currentTimeMillis().toString()
    override val direction: MessageDirection = MessageDirection.OUTGOING

    fun setData(data: Any) {
        this.data = data
    }

    fun toIncomingMessage(): IncomingMessage {
        return IncomingMessage(this.id, data)
    }
}