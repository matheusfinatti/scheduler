package com.example.scheduler.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.scheduler.domain.model.SpaceCalendarEntriesState
import com.example.scheduler.domain.model.SpaceCalendarEntry
import java.util.Date
import java.util.TimeZone
import com.example.core.R as coreR

@Composable
fun SchedulerScreen(viewModel: SchedulerViewModel) {
    val viewState by remember(viewModel) { viewModel.getEntries() }
        .collectAsState(initial = SchedulerViewState.Empty)

    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        when (viewState) {
            SchedulerViewState.Empty -> Unit
            is SchedulerViewState.Error -> Error(viewState as SchedulerViewState.Error)
            is SchedulerViewState.List -> Spaces(viewState as SchedulerViewState.List)
            SchedulerViewState.Loading -> Loading()
        }
    }
}

@Composable
fun Spaces(viewState: SchedulerViewState.List) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        viewState.entries.forEach { entry ->
            item {
                SpaceCard(entry = entry, Modifier.fillParentMaxWidth())
            }
        }

    }
}

@Composable
fun SpaceCard(entry: SpaceCalendarEntry, modifier: Modifier) {
    Column(modifier) {
       Card(
           modifier = Modifier.fillMaxWidth().aspectRatio(1.5f),
           elevation = 4.dp,
       ) {
           AsyncImage(
               model = ImageRequest.Builder(LocalContext.current)
                   .data(entry.image)
                   .crossfade(true)
                   .size(Size.ORIGINAL)
                   .placeholder(coreR.drawable.placeholder)
                   .build(),
               contentDescription = null,
               modifier = Modifier.fillMaxSize(),
               contentScale = ContentScale.Crop
           )
       }
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = entry.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(Color.Gray)
    )
}

@Composable
fun Error(viewState: SchedulerViewState.Error) {
    Text(text = viewState.message ?: "No message")
}

@Composable
fun SpacePicture(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun ListPreview() {
    val viewState = SchedulerViewState.List(
        state = SpaceCalendarEntriesState.Entries(
            listOf(
                SpaceCalendarEntry(
                    startTime = Date(),
                    endTime = Date(),
                    name = "Space 1",
                    image = "",
                    timezone = TimeZone.getDefault(),
                ),
                SpaceCalendarEntry(
                    startTime = Date(),
                    endTime = Date(),
                    name = "Space 2",
                    image = "",
                    timezone = TimeZone.getDefault(),
                ),
            )
        )
    )

    Spaces(viewState = viewState)
}

@Preview
@Composable
private fun SpaceCardPreview() {
    val entry = SpaceCalendarEntry(
        startTime = Date(),
        endTime = Date(),
        name = "Space 1",
        image = "",
        timezone = TimeZone.getDefault(),
    )

    SpaceCard(entry = entry, Modifier.fillMaxWidth())
}
