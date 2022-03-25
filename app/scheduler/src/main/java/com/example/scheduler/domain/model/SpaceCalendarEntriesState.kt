package com.example.scheduler.domain.model

/**
 * Defines all states of a request to load the calendar entries state.
 */
sealed class SpaceCalendarEntriesState {

    /**
     * Request is loading.
     */
    object Loading : SpaceCalendarEntriesState()

    /**
     * Successful response.
     *
     * @property entries the returned entries.
     */
    data class Entries(val entries: List<SpaceCalendarEntry>) : SpaceCalendarEntriesState()

    /**
     * Error response.
     *
     * @property error the thrown exception.
     */
    data class Error(val error: Throwable) : SpaceCalendarEntriesState()
}
