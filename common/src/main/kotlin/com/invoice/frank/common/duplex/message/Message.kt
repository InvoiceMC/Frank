package com.invoice.frank.common.duplex.message

import java.io.Serializable

interface Message: Serializable {
    val id: String
    val direction: MessageDirection
    val data: Any
}