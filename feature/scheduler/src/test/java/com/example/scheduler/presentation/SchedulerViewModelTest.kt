package com.example.scheduler.presentation

import com.example.scheduler.domain.model.OfficeSpace
import com.example.scheduler.domain.usecases.GetAllSpacesEntriesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SchedulerViewModelTest {

    private val usecase = mockk<GetAllSpacesEntriesUseCase>(relaxed = true)
    private val viewModel = SchedulerViewModel(usecase)
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given a list of entries, when getting it, it should emit a list view state`() = runTest {
        // given
        coEvery {
            usecase.execute()
        } returns flowOf(emptyMap())

        // when
        val viewState = viewModel.getEntries().drop(1).first()

        // then
        assertTrue(viewState is SchedulerViewState.Entries)
        assertEquals(emptySet<OfficeSpace>(), (viewState as SchedulerViewState.Entries).spaces)
    }

    @Test
    fun `when getting the entries, it should emit a loading view state`() = runTest {
        // when
        val viewState = viewModel.getEntries().first()

        // then
        assertTrue(viewState is SchedulerViewState.Loading)
    }

    @Test
    fun `given an error, when getting it, it should emit an error view state`() = runTest {
        // given
        val exception = Exception("Test")
        coEvery {
            usecase.execute()
        } throws exception

        // when
        val viewState = viewModel.getEntries().drop(1).first()

        // then
        assertTrue(viewState is SchedulerViewState.Error)
        assertEquals(exception.message, (viewState as SchedulerViewState.Error).message)
    }
}
