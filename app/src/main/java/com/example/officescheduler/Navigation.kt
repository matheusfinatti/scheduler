@file:Suppress("UndocumentedPublicFunction", "FunctionNaming")
package com.example.officescheduler

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigation.SchedulerDirections
import com.example.scheduler.presentation.OfficesScreen
import com.example.scheduler.presentation.SchedulerScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SchedulerDirections.offices.destination
    ) {

        composable(SchedulerDirections.offices.destination) {
            OfficesScreen(viewModel = getViewModel(owner = LocalContext.current as ComponentActivity))
        }

        composable(
            SchedulerDirections.scheduler.destination,
            arguments = SchedulerDirections.scheduler.args
        ) { backStackEntry ->
            val arg = backStackEntry.arguments
                ?.getInt(SchedulerDirections.scheduler.args[0].name) ?: 0

            SchedulerScreen(
                spaceId = arg,
                viewModel = getViewModel(owner = LocalContext.current as ComponentActivity)
            )
        }
    }
}
