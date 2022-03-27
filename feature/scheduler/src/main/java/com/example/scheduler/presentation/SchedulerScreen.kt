package com.example.scheduler.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scheduler.domain.model.SpaceCalendarEntry
import com.example.scheduler.presentation.ui.SpaceCard

@Composable
fun SchedulerScreen(spaceId: Int, viewModel: SchedulerViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    val spaceState by remember {
        mutableStateOf(
            (viewState as? SchedulerViewState.Entries)?.spaces?.find { it.id == spaceId }
        )
    }

    val space = spaceState ?: return // Would actually like to show an error here.

    val entries by remember {
        mutableStateOf(
            (viewState as? SchedulerViewState.Entries)?.getCalendar(space) ?: emptyList()
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(space.name) })
        },
        content = {
            Column(Modifier.fillMaxWidth()) {
                SpaceCard(officeSpace = space, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "Unavailable dates", Modifier.padding(8.dp))
                Spacer(modifier = Modifier.size(16.dp))
                CalendarEntries(
                    entries = entries,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    )
}

@Composable
fun CalendarEntries(entries: List<SpaceCalendarEntry>, modifier: Modifier) {
    LazyColumn(modifier) {
        items(entries) { entry ->
            Row {
                Text(text = String.format("From: %ta %td %tk:%tM", entry.startTime, entry.startTime, entry.startTime, entry.startTime))
                Spacer(Modifier.size(8.dp))
                Text(text = String.format("To: %ta %td %tk:%tM", entry.endTime, entry.endTime, entry.endTime, entry.endTime))
            }
        }
    }
}
