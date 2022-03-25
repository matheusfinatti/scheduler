package com.example.scheduler.data.remote

import com.example.scheduler.data.remote.model.EntriesDataModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * API access to the spaces api.
 */
internal interface SpacesApi {

    @GET("offices")
    suspend fun getSpaces(): Response<EntriesDataModel>
}
