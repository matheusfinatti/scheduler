package com.example.scheduler.presentation

import com.example.scheduler.domain.model.OfficeSpace
import com.example.scheduler.domain.model.SpaceCalendarEntry
import java.time.LocalDateTime
import java.util.Locale

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

        /**
         * Gets the calendar of a specific office space.
         *
         * @return a list of [SpaceCalendarEntry].
         */
        fun getCalendar(officeSpace: OfficeSpace) = entries[officeSpace]

        /**
         * Gets a formatted date string for a given [LocalDateTime].
         *
         * @param time a [LocalDateTime] to format.
         * @return a formatted date string for the given [LocalDateTime].
         */
        fun getDateString(time: LocalDateTime) =
            String.format(
                Locale.getDefault(),
                "%ta %td %tk:%tM",
                time,
                time,
                time,
                time,
            )
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
