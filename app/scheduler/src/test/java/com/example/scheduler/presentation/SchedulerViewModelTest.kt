package com.example.scheduler.presentation

import com.example.scheduler.domain.model.SpaceCalendarEntriesState
import com.example.scheduler.domain.model.SpaceCalendarEntry
import com.example.scheduler.domain.usecases.GetAllSpacesEntriesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SchedulerViewModelTest {

    private val usecase = mockk<GetAllSpacesEntriesUseCase>()
    private val dispatcher = TestCoroutineDispatcher()
    private val viewModel = SchedulerViewModel(dispatcher, usecase)

    @Test
    fun `given a list of entries, when getting it, it should emit a list view state`() = runBlockingTest {
        // given
        coEvery {
            usecase.execute()
        } returns flowOf(SpaceCalendarEntriesState.Entries(emptyList()))

        // when
        val viewState = viewModel.getEntries().first()

        // then
        assertTrue(viewState is SchedulerViewState.List)
        assertEquals(emptyList<SpaceCalendarEntry>(), (viewState as SchedulerViewState.List).entries)
    }

    @Test
    fun `given it's loading, when getting it, it should emit a loading view state`() = runBlockingTest {
        // given
        coEvery {
            usecase.execute()
        } returns flowOf(SpaceCalendarEntriesState.Loading)

        // when
        val viewState = viewModel.getEntries().first()

        // then
        assertTrue(viewState is SchedulerViewState.Loading)
    }

    @Test
    fun `given an error, when getting it, it should emit an error view state`() = runBlockingTest {
        // given
        val exception = Exception("Test")
        coEvery {
            usecase.execute()
        } returns flowOf(SpaceCalendarEntriesState.Error(exception))

        // when
        val viewState = viewModel.getEntries().first()

        // then
        assertTrue(viewState is SchedulerViewState.Error)
        assertEquals(exception.message, (viewState as SchedulerViewState.Error).message)
    }
}
