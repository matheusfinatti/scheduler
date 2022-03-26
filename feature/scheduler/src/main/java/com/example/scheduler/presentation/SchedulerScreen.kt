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
}
