package com.example.scheduler.domain.model

import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import java.util.TimeZone

data class OfficeSpace(
    val name: String,
    val image: String,
    val timezone: TimeZone,
) {
    /**
     * Special constructor that allows us to build this model with the equivalent data model.
     */
    constructor(dataModel: SpaceEntryDataModel) : this(
        name = dataModel.name,
        image = dataModel.image,
        timezone = TimeZone.getTimeZone(dataModel.timezone),
    )
}
