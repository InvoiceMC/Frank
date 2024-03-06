package com.invoice.frank.common.duplex.punishments

import java.io.Serializable

enum class Punishment (
    val cleanName: String,
    val defaultReason: String,
    val type: PunishmentType,
    val incrementationPattern: IncrementationPattern
): Serializable {
    KICK("Kick", "Kicked by a staff member", PunishmentType.KICK, IncrementationPattern.NONE),
    CHEATS("Cheats", "Cheats are not allowed", PunishmentType.BAN, IncrementationPattern.HIGH),
    SPAMMING("Spamming", "Restrain from spamming or flooding the chat", PunishmentType.MUTE, IncrementationPattern.TRIVIAL),
    SWEARING("Swearing", "Refrain from being profane", PunishmentType.MUTE, IncrementationPattern.MEDIUM),
    ADVERTISING("Advertising", "Advertising is prohibited", PunishmentType.MUTE, IncrementationPattern.HIGH),
    TROLLING("Trolling", "Stop the trolls", PunishmentType.MUTE, IncrementationPattern.TRIVIAL),
    DISCRIMINATION("Discrimination", "Discriminatory behavior is prohibited", PunishmentType.BAN, IncrementationPattern.SEVERE),
    DEATH_THREATS("Death threats", "Death threats are prohibited", PunishmentType.BAN, IncrementationPattern.SEVERE),
    INAPPROPRIATE("Inappropriate", "Inappropriate behavior is not allowed", PunishmentType.BAN, IncrementationPattern.HIGH),
    OTHER_PERMANENT("Other permanent", "Other", PunishmentType.BAN, IncrementationPattern.EXTREME),
}