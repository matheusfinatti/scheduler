package com.example.scheduler.presentation

import androidx.lifecycle.ViewModel
import com.example.scheduler.domain.usecases.GetAllSpacesEntriesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

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

         getAllSpacesEntriesUseCase.execute().collect { entries ->
             emit(SchedulerViewState.Entries(entries))
         }
     }.catch { error ->
        emit(SchedulerViewState.Error(error))
    }
}
