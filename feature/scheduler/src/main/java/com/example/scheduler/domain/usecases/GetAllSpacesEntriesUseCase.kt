package com.example.scheduler.domain.usecases

import com.example.scheduler.domain.repository.SpacesRepository

/**
 * Use case used for getting all spaces & their calendar entries.
 *
 * @property repository the [SpacesRepository] used to fetch the spaces calendars.
 */
class GetAllSpacesEntriesUseCase(
    private val repository: SpacesRepository,
) {

    /**
     * Executes the use case.
     *
     * @return a [Flow] that emits the the [SpaceCalendarEntriesState].
     */
    suspend fun execute() = repository.getSpaces()
}
