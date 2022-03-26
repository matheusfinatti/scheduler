package com.example.scheduler.data.repository

import com.example.scheduler.data.remote.SpacesApi
import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import com.example.scheduler.domain.model.SpaceCalendarEntry
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SpacesRepositoryImplTest {

    private val spacesApi = mockk<SpacesApi>(relaxed = true)
    private val repository = SpacesRepositoryImpl(UnconfinedTestDispatcher(), spacesApi)

    private val dataModel = SpaceEntryDataModel(
        "2022-03-10T09:00:00.000Z",
        "2022-03-10T17:00:00.000Z",
        "name",
        "image",
        "Europe/London"
    )
    private val responseModel = listOf(dataModel)

    @Test
    fun `given a list of entries, when requesting, then this list should be emitted`() = runTest {
        // given
        coEvery { spacesApi.getSpaces() } returns Response.success(responseModel)

        // when
        val result = repository.getSpaces().first()

        // then
        assertEquals(responseModel, result)
    }

    @Test
    fun `given an empty response, when requesting, then empty list should be emitted`() = runTest {
        // given
        coEvery { spacesApi.getSpaces() } returns Response.success(null)

        // when
        val result = repository.getSpaces().first()

        // then
        assertEquals(emptyList<SpaceCalendarEntry>(), result)
    }
}
