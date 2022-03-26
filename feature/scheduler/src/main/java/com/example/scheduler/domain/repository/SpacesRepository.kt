package com.example.scheduler.domain.repository

import com.example.scheduler.domain.model.SpaceCalendarEntriesState
import kotlinx.coroutines.flow.Flow

/**
 * Defines a repository for getting spaces & their calendars.
 */
interface SpacesRepository {

    /**
     * Gets all spaces & their calendar entries.
     *
     * @return a flow that emits a list of [SpaceCalendarEntriesState] containing the state of
     * the load request.
     */
    suspend fun getSpaces(): Flow<SpaceCalendarEntriesState>
}
