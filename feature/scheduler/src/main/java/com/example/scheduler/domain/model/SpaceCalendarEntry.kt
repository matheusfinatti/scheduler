package com.example.scheduler.domain.model

import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * A calendar entry for an office space.
 */
data class SpaceCalendarEntry(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
) {

    /**
     * Special constructor that allows us to build this model with the equivalent data model.
     */
    constructor(dataModel: SpaceEntryDataModel) : this(
        startTime = LocalDateTime.from(
            Instant.parse(dataModel.startTime)
                .atZone(ZoneId.of(dataModel.timezone))
                .toLocalDateTime()
        ),
        endTime = LocalDateTime.from(
            Instant.parse(dataModel.endTime)
                .atZone(ZoneId.of(dataModel.timezone))
                .toLocalDateTime()
        ),
    )
}
