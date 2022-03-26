package com.example.scheduler.presentation

import com.example.scheduler.domain.model.OfficeSpace
import com.example.scheduler.domain.model.SpaceCalendarEntry

/**
 * View state for the scheduler.
 */
sealed class SchedulerViewState {

    /**
     * Loading.
     */
    object Loading : SchedulerViewState()

    /**
     * Empty view state.
     */
    object Empty : SchedulerViewState()

    /**
     * List of spaces & calendar entries.
     *
     * @property entries a map with spaces and their calendar entries.
     */
    class Entries(
        private val entries: Map<OfficeSpace, List<SpaceCalendarEntry>>,
    ) : SchedulerViewState() {

        val spaces = entries.keys

        fun getCalendar(officeSpace: OfficeSpace) = entries[officeSpace]
    }

    /**
     * Error state.
     *
     * @property error the throwable.
     */
    class Error(private val error: Throwable) : SchedulerViewState() {
        val message = error.message
    }
}
