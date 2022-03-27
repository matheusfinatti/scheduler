package com.example.scheduler.presentation

import app.cash.turbine.test
import com.example.navigation.NavigationManager
import com.example.navigation.SchedulerDirections
import com.example.scheduler.domain.model.OfficeSpace
import com.example.scheduler.domain.usecases.GetAllSpacesEntriesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flow
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
import java.util.TimeZone

@ExperimentalCoroutinesApi
class SchedulerViewModelTest {

    private val usecase = mockk<GetAllSpacesEntriesUseCase>(relaxed = true)
    private val navigationManager = mockk<NavigationManager>(relaxed = true)
    private val viewModel = SchedulerViewModel(navigationManager, usecase)
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
            usecase.invoke()
        } returns flowOf(emptyMap())

        viewModel.viewState.drop(1).test {
            // when
            viewModel.getEntries()

            // then
            val viewState = awaitItem()

            assertTrue(viewState is SchedulerViewState.Entries)
            assertEquals(emptySet<OfficeSpace>(), (viewState as SchedulerViewState.Entries).spaces)
        }
    }

    @Test
    fun `when getting the entries, it should emit a loading view state`() = runTest {
        viewModel.viewState.drop(1).test {
            // when
            viewModel.getEntries()

            // then
            val viewState = awaitItem()
            assertTrue(viewState is SchedulerViewState.Loading)
        }
    }

    @Test
    fun `given an error, when getting it, it should emit an error view state`() = runTest {
        // given
        val exception = Exception("Test")
        coEvery {
            usecase.invoke()
        } returns flow { throw exception }

        viewModel.viewState.drop(1).test {
            // when
            viewModel.getEntries()

            // then
            val viewState = awaitItem()
            assertTrue(viewState is SchedulerViewState.Error)
            assertEquals(exception.message, (viewState as SchedulerViewState.Error).message)
        }
    }

    @Test
    fun `given a space, when a it's clicked, then it should navigate to scheduler`() {
        // given
        val space = OfficeSpace(id = 1, name = "Test", image = "", timezone = TimeZone.getDefault())

        // when
        viewModel.onClickOfficeSpace(space)

        // then
        verify {
            navigationManager.navigate(SchedulerDirections.scheduler, "1")
        }
    }
}
