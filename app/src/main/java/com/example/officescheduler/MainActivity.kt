@file:Suppress("UndocumentedPublicFunction", "FunctionNaming")
package com.example.officescheduler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigation.NavigationManager
import com.example.officescheduler.ui.theme.OfficeSchedulerTheme
import org.koin.android.ext.android.inject

/**
 * Main app activity.
 */
class MainActivity : ComponentActivity() {

    private val navigationManager by inject<NavigationManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfficeSchedulerTheme {
                val navController = rememberNavController()
                navigationManager.commands.collectAsState().value.also { command ->
                    if (command.destination.isNotEmpty()) {
                        // This is crashing when orientation changes. Not sure why.
                        navController.navigate(command.destination)
                    }
                }

                AppNavigation(navController = navController)
            }
        }
    }
}
