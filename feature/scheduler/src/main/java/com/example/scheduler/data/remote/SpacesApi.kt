package com.example.scheduler.data.remote

import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * API access to the spaces api.
 */
internal interface SpacesApi {

    /**
     * Gets spaces and entries in their calendars.
     *
     * @return a [Response] with the spaces and entries.
     */
    @GET("offices")
    suspend fun getSpaces(): Response<List<SpaceEntryDataModel>>
}
