package com.example.scheduler.data.repository

import com.example.scheduler.data.remote.SpacesApi
import com.example.scheduler.data.remote.model.EntriesDataModel
import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import com.example.scheduler.domain.model.SpaceCalendarEntriesState
import com.example.scheduler.domain.model.SpaceCalendarEntry
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class SpacesRepositoryImplTest {

    private val spacesApi = mockk<SpacesApi>(relaxed = true)
    private val repository = SpacesRepositoryImpl(TestCoroutineDispatcher(), spacesApi)

    private val dataModel = SpaceEntryDataModel(
        "2022-03-10T09:00:00.000Z",
        "2022-03-10T17:00:00.000Z",
        "name",
        "image",
        "Europe/London"
    )
    private val responseModel = EntriesDataModel(
        listOf(dataModel)
    )

    private val expectedModel = listOf(SpaceCalendarEntry(dataModel))

    @Test
    fun `when requesting, then it should emit loading state first`() = runBlockingTest {
        // when
        val result = repository.getSpaces()

        // then
        val firstItem = result.first()
        assertEquals(SpaceCalendarEntriesState.Loading, firstItem)
    }

    @Test
    fun `given a list of entries, when requesting, then this list should be emitted`() = runBlockingTest {
        // given
        coEvery { spacesApi.getSpaces() } returns Response.success(responseModel)

        // when
        val result = repository.getSpaces().drop(1).first()

        // then
        val entries = (result as SpaceCalendarEntriesState.Entries).entries
        assertEquals(expectedModel, entries)
    }

    @Test
    fun `given an empty response, when requesting, then empty list should be emitted`() = runBlockingTest {
        // given
        coEvery { spacesApi.getSpaces() } returns Response.success(null)

        // when
        val result = repository.getSpaces().drop(1).first()

        // then
        val entries = (result as SpaceCalendarEntriesState.Entries).entries
        assertEquals(emptyList<SpaceCalendarEntry>(), entries)
    }

    @Test(expected = Exception::class)
    fun `given an error, when requesting, then this error should be emitted`() = runBlockingTest {
        // given
        coEvery { spacesApi.getSpaces() } returns Response.error(500, EMPTY_RESPONSE)

        // when
        val result = repository.getSpaces().drop(1).first()

        // then
        val error = (result as SpaceCalendarEntriesState.Error).error
        throw error
    }
}
