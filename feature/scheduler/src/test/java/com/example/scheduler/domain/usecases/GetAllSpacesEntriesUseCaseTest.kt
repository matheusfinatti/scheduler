package com.example.scheduler.domain.usecases

import com.example.scheduler.data.remote.model.SpaceEntryDataModel
import com.example.scheduler.domain.model.OfficeSpace
import com.example.scheduler.domain.model.SpaceCalendarEntry
import com.example.scheduler.domain.repository.SpacesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllSpacesEntriesUseCaseTest {

    private val repository = mockk<SpacesRepository>()
    private val usecase = GetAllSpacesEntriesUseCase(repository)

    @Test
    fun `given a list of calendar entries, when executing, then map it into spaces and their entries`() = runTest {
        // given
        val entries = listOf(
            SpaceEntryDataModel(
                "2022-03-10T09:00:00.000Z",
                "2022-03-10T17:00:00.000Z",
                "space 1",
                "image",
                "Europe/London"
            ),
            SpaceEntryDataModel(
                "2022-03-10T18:00:00.000Z",
                "2022-03-10T23:00:00.000Z",
                "space 1",
                "image",
                "Europe/London"
            ),
            SpaceEntryDataModel(
                "2022-03-10T09:00:00.000Z",
                "2022-03-10T17:00:00.000Z",
                "space 2",
                "image",
                "Europe/London"
            ),
        )

        coEvery { repository.getSpaces() } returns flowOf(entries)

        // when
        val result = usecase.execute().first()

        // then
        val expectedResult = mapOf(
            OfficeSpace(entries[0]) to listOf(SpaceCalendarEntry(entries[0]), SpaceCalendarEntry(entries[1])),
            OfficeSpace(entries[2]) to listOf(SpaceCalendarEntry(entries[2])),
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun `given an empty list of calendar entries, when executing, then map it to an empty map`() = runTest {
        // given
        coEvery { repository.getSpaces() } returns flowOf(emptyList())

        // when
        val result = usecase.execute().first()

        // then
        assertEquals(emptyMap<OfficeSpace, List<SpaceCalendarEntry>>(), result)
    }
}
