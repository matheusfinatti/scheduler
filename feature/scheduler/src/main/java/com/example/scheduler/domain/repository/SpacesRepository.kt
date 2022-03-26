package com.example.scheduler.domain.repository

import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import kotlinx.coroutines.flow.Flow

/**
 * Defines a repository for getting spaces & their calendars.
 */
interface SpacesRepository {

    /**
     * Gets all spaces & their calendar entries.
     *
     * @return a flow that emits a list of [SpaceEntryDataModel] or throws if there's an error.
     */
    suspend fun getSpaces(): Flow<List<SpaceEntryDataModel>>
}
