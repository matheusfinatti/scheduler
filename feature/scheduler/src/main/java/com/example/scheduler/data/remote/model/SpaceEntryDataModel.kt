package com.example.scheduler.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Describes the data model for a space.
 */
@JsonClass(generateAdapter = true)
data class SpaceEntryDataModel(
    @Json(name = "starts_at") val startTime: String,
    @Json(name = "ends_at") val endTime: String,
    @Json(name = "space_name") val name: String,
    @Json(name = "space_image") val image: String,
    @Json(name = "space_timezone") val timezone: String,
)
