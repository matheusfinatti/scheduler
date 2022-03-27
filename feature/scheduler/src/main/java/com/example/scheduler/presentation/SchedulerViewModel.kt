package com.example.scheduler.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.NavigationManager
import com.example.navigation.SchedulerDirections
import com.example.scheduler.domain.model.OfficeSpace
import com.example.scheduler.domain.usecases.GetAllSpacesEntriesUseCase
import com.example.scheduler.presentation.ui.OnOfficeSpaceInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * View model for the scheduler screen.
 */
class SchedulerViewModel(
    private val navigationManager: NavigationManager,
    private val getAllSpacesEntriesUseCase: GetAllSpacesEntriesUseCase,
) : ViewModel(), OnOfficeSpaceInteractor {

    private val state = MutableStateFlow<SchedulerViewState>(SchedulerViewState.Empty)

    /**
     * Provides a flow for querying the current view state.
     */
    val viewState: StateFlow<SchedulerViewState> = state

    /**
     * Requests the spaces & calendar entries. View state is emitted in [viewState] flow.
     */
     fun getEntries() = viewModelScope.launch {
            state.value = SchedulerViewState.Loading

            getAllSpacesEntriesUseCase()
                .catch { error ->
                    state.value = SchedulerViewState.Error(error)
                }.collect { entries ->
                    state.value = SchedulerViewState.Entries(entries)
                }
        }

    override fun onClickOfficeSpace(space: OfficeSpace) {
        navigationManager.navigate(SchedulerDirections.scheduler, "${space.id}")
    }
}
