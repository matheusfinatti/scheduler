package com.example.scheduler.presentation

import androidx.lifecycle.ViewModel
import com.example.scheduler.domain.usecases.GetAllSpacesEntriesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * View model for the scheduler screen.
 */
class SchedulerViewModel(
    private val getAllSpacesEntriesUseCase: GetAllSpacesEntriesUseCase,
) : ViewModel() {

    /**
     * Requests the spaces & calendar entries.
     *
     * @return a [Flow] that emits the current view state of the request.
     */
     fun getEntries() = flow {
         emit(SchedulerViewState.Loading)

         getAllSpacesEntriesUseCase.execute().map { state ->
             SchedulerViewState.fromState(state)
         }.collect(::emit)
     }
}
