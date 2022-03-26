package com.example.scheduler.presentation

import com.example.scheduler.domain.model.SpaceCalendarEntriesState

/**
 * View state for the scheduler.
 */
sealed class SchedulerViewState {

    /**
     * Loading.
     */
    object Loading : SchedulerViewState()

    /**
     * List of spaces & calendar entries.
     *
     * @property state state with a list of entries.
     */
    class List(private val state: SpaceCalendarEntriesState.Entries) : SchedulerViewState() {
        val entries = state.entries
    }

    /**
     * Error state.
     *
     * @property state error state.
     */
    class Error(private val state: SpaceCalendarEntriesState.Error) : SchedulerViewState() {
        val message = state.error.message
    }

    companion object {

        /**
         * Gets the view state relative to the response model.
         *
         * @param state the current state.
         */
        fun fromState(state: SpaceCalendarEntriesState) =
            when (state) {
                is SpaceCalendarEntriesState.Entries -> List(state)
                is SpaceCalendarEntriesState.Error -> Error(state)
                SpaceCalendarEntriesState.Loading -> Loading
            }
    }
}
