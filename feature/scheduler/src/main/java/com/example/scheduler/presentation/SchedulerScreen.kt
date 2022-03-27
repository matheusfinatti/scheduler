package com.example.scheduler.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.core.log.Log

@Composable
fun SchedulerScreen(spaceId: Int, viewModel: SchedulerViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("viewState: $viewState")
}
