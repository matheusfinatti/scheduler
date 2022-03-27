package com.example.scheduler.domain.model

import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import java.util.TimeZone

/**
 * Office space model.
 */
data class OfficeSpace(
    val id: Int,
    val name: String,
    val image: String,
    val timezone: TimeZone,
) {
    /**
     * Special constructor that allows us to build this model with the equivalent data model.
     */
    constructor(dataModel: SpaceEntryDataModel) : this(
        id = dataModel.name.hashCode(), // Because we don't have a proper id in this exercise.
        name = dataModel.name,
        image = dataModel.image,
        timezone = TimeZone.getTimeZone(dataModel.timezone),
    )
}
