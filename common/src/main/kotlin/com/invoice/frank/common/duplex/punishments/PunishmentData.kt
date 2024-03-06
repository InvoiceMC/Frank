package com.invoice.frank.common.duplex.punishments

import me.outspending.munch.Column
import me.outspending.munch.ColumnConstraint
import me.outspending.munch.PrimaryKey
import me.outspending.munch.Table
import java.io.Serializable
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Table("punishments")
data class PunishmentData(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @Column val reason: String?,
    @Column(constraints = [ColumnConstraint.NOTNULL]) val starts: Long,
    @Column(constraints = [ColumnConstraint.NOTNULL]) val ends: Long,
    @Column(constraints = [ColumnConstraint.NOTNULL]) val offender: UUID,
    @Column val punisher: UUID?,
    @Column(constraints = [ColumnConstraint.NOTNULL]) val type: PunishmentType,
    @Column(constraints = [ColumnConstraint.NOTNULL]) val silent: Boolean = false,
    @Column(constraints = [ColumnConstraint.NOTNULL]) val punishment: Punishment
): Serializable {
    fun isActive(): Boolean {
        return ends > System.currentTimeMillis()
    }
    fun getDuration(): Duration {
        return (ends - starts).toDuration(DurationUnit.MILLISECONDS)
    }
}