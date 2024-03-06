package com.invoice.frank.common.duplex.message

import java.util.UUID

class IncomingMessage(
    override val id: UUID,
    override val data: Any
): Message {
    override val direction: MessageDirection = MessageDirection.INCOMING
}