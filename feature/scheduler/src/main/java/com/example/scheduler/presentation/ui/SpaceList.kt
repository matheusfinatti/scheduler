package com.example.scheduler.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scheduler.domain.model.OfficeSpace
import com.example.scheduler.domain.model.SpaceCalendarEntry
import com.example.scheduler.presentation.SchedulerViewState
import java.util.Date
import java.util.TimeZone

@Composable
fun SpaceList(viewState: SchedulerViewState.Entries, onItemClick: (OfficeSpace) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        viewState.spaces.forEach { space ->
            item {
                SpaceCard(officeSpace = space,
                    Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            onItemClick(space)
                        }
                )
            }
        }

    }
}

@Preview
@Composable
private fun ListPreview() {
    val viewState = SchedulerViewState.Entries(
        entries = mapOf(
            OfficeSpace(
                id = 1,
                name = "Space 1",
                image = "",
                timezone = TimeZone.getDefault(),
            ) to listOf(
                SpaceCalendarEntry(
                    startTime = Date(),
                    endTime = Date(),
                ),
            ),
            OfficeSpace(
                id = 2,
                name = "Space 2",
                image = "",
                timezone = TimeZone.getDefault(),
            ) to listOf(
                SpaceCalendarEntry(
                    startTime = Date(),
                    endTime = Date(),
                ),
            ),
        )
    )

    SpaceList(viewState = viewState) { }
}
