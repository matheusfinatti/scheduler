package com.example.scheduler.data.remote.model

import com.squareup.moshi.JsonClass

/**
 * Describes the data model for a list of scheduled spaces.
 */
@JsonClass(generateAdapter = true)
data class EntriesDataModel(
    val entries: List<SpaceEntryDataModel>
)
