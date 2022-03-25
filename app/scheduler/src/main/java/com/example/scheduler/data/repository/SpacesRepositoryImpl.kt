package com.example.scheduler.data.repository

import com.example.core.network.stringSuspending
import com.example.scheduler.data.remote.SpacesApi
import com.example.scheduler.domain.model.SpaceCalendarEntriesState
import com.example.scheduler.domain.model.SpaceCalendarEntry
import com.example.scheduler.domain.repository.SpacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class SpacesRepositoryImpl(
    private val dispatcher: CoroutineContext,
    private val spacesApi: SpacesApi,
) : SpacesRepository {

    @Suppress("TooGenericExceptionThrown")
    override suspend fun getSpaces(): Flow<SpaceCalendarEntriesState> =
        withContext(dispatcher) {
            flow {
                emit(SpaceCalendarEntriesState.Loading)

                val response = spacesApi.getSpaces()
                if (response.isSuccessful) {
                    val entries = response.body()?.entries?.map { dataModel ->
                        SpaceCalendarEntry(dataModel)
                    } ?: emptyList()

                    emit(SpaceCalendarEntriesState.Entries(entries))
                } else {
                    throw Exception(response.errorBody()?.stringSuspending())
                }
            }.catch { e ->
                emit(SpaceCalendarEntriesState.Error(e))
            }
        }
}
