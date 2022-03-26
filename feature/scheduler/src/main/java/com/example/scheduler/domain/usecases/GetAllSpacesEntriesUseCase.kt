package com.example.scheduler.domain.usecases

import com.example.scheduler.domain.model.OfficeSpace
import com.example.scheduler.domain.model.SpaceCalendarEntry
import com.example.scheduler.domain.repository.SpacesRepository
import kotlinx.coroutines.flow.map

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
     * Transforms the list of entries into a `Map<Space, List<SpaceCalendarEntry>>`.
     * Each entry of the Map is a space, and the value is a list of calendar entries for that space.
     *
     * @return a [Flow] that emits the the map of entries.
     */
    suspend fun execute() = repository.getSpaces().map {  list ->
        list.groupBy { dataModel ->
            dataModel.name
        }.mapKeys { entry ->
            OfficeSpace(entry.value[0])
        }.mapValues { entry ->
            entry.value.map { SpaceCalendarEntry(it) }
        }
    }
}
