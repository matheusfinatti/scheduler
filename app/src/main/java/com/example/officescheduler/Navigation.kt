package com.example.officescheduler

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.navigation.SchedulerDirections
import com.example.scheduler.presentation.SchedulerScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SchedulerDirections.root.destination
    ) {
        navigation(
            startDestination = SchedulerDirections.scheduler.destination,
            route = SchedulerDirections.root.destination
        ) {
            composable(SchedulerDirections.scheduler.destination) {
                SchedulerScreen(viewModel = getViewModel())
            }
        }
    }
}
