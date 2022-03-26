package com.example.scheduler.domain.model

import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import java.time.Instant
import java.util.Date

/**
 * A calendar entry for an office space.
 */
data class SpaceCalendarEntry(
    val startTime: Date,
    val endTime: Date,
) {

    /**
     * Special constructor that allows us to build this model with the equivalent data model.
     */
    constructor(dataModel: SpaceEntryDataModel) : this(
        startTime = Date.from(Instant.parse(dataModel.startTime)),
        endTime = Date.from(Instant.parse(dataModel.endTime)),
    )
}
