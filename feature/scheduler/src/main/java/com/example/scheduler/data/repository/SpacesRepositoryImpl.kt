package com.example.scheduler.data.repository

import com.example.core.network.stringSuspending
import com.example.scheduler.data.remote.SpacesApi
import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import com.example.scheduler.domain.repository.SpacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class SpacesRepositoryImpl(
    private val dispatcher: CoroutineContext,
    private val spacesApi: SpacesApi,
) : SpacesRepository {

    @Suppress("TooGenericExceptionThrown")
    override suspend fun getSpaces(): Flow<List<SpaceEntryDataModel>> =
        withContext(dispatcher) {
            flow {
                val response = spacesApi.getSpaces()
                if (response.isSuccessful) {
                    val entries = response.body() ?: emptyList()

                    emit(entries)
                } else {
                    val errorMessage = response.errorBody()?.stringSuspending()
                    throw Exception(errorMessage)
                }
            }
        }
}
