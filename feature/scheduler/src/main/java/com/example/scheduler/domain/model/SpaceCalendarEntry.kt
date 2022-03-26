package com.example.scheduler.domain.model

import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import java.time.Instant
import java.util.Date
import java.util.TimeZone

/**
 * A calendar entry for an office space.
 */
data class SpaceCalendarEntry(
    val startTime: Date,
    val endTime: Date,
    val name: String,
    val image: String,
    val timezone: TimeZone,
) {

    /**
     * Special constructor that allows us to build this model with the equivalent data model.
     */
    constructor(dataModel: SpaceEntryDataModel) : this(
        startTime = Date.from(Instant.parse(dataModel.startTime)),
        endTime = Date.from(Instant.parse(dataModel.endTime)),
        name = dataModel.name,
        image = dataModel.image,
        timezone = TimeZone.getTimeZone(dataModel.timezone),
    )
}
