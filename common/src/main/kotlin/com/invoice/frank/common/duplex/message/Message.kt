package com.invoice.frank.common.duplex.message

import java.io.Serializable
import java.util.UUID

interface Message: Serializable {
    val id: UUID
    val direction: MessageDirection
    val data: Any
}