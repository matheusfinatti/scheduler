package com.example.scheduler.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.scheduler.presentation.ui.SpaceList

@Composable
fun OfficesScreen(viewModel: SchedulerViewModel) {
    val viewState by viewModel.viewState.collectAsState()
    remember { viewModel.getEntries() } // Not sure how to avoid re-calling on config changes

    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        when (viewState) {
            SchedulerViewState.Empty -> Unit
            is SchedulerViewState.Error -> Error(viewState as SchedulerViewState.Error)
            is SchedulerViewState.Entries ->
                SpaceList(viewState as SchedulerViewState.Entries, viewModel::onClickOfficeSpace)
            SchedulerViewState.Loading -> Loading()
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
    Text(
        text = viewState.message ?: "No message",
        color = Color.Red,
    )
}
