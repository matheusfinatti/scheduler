package com.example.scheduler.presentation

import androidx.lifecycle.ViewModel
import com.example.scheduler.domain.repository.SpacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class SchedulerViewModel(
    private val dispatcher: CoroutineContext,
    private val repository: SpacesRepository,
) : ViewModel() {

    /**
     * Gets a flow that emits the view state based on the repository returned state.
     *
     * @return a [Flow] that emits [SchedulerViewState]s.
     */
    suspend fun getEntries(): Flow<SchedulerViewState> = withContext(dispatcher) {
        repository.getSpaces().map { state ->
            SchedulerViewState.fromState(state)
        }
    }
}
