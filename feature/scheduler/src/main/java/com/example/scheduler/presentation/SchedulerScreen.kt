package com.example.scheduler.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun SchedulerScreen(viewModel: SchedulerViewModel) {
    val viewState by remember(viewModel) { viewModel.getEntries() }
        .collectAsState(initial = SchedulerViewState.Empty)

    Text("Scheduler ${viewState::class.simpleName}")

    when (val vs = viewState) {
        SchedulerViewState.Empty -> Text("Empty")
        is SchedulerViewState.Error -> Text(text = "Error: ${vs.message}")
        is SchedulerViewState.List -> Text(text = "List: ${vs.entries.map { it.name }}")
        SchedulerViewState.Loading -> Text(text = "Loading")
    }
}
