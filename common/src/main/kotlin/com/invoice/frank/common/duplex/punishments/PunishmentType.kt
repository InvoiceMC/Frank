package com.invoice.frank.common.duplex.punishments

import java.io.Serializable

enum class PunishmentType(
    val cleanName: String,
): Serializable {
    KICK("Kick"),
    BAN("Ban"),
    MUTE("Mute")
}