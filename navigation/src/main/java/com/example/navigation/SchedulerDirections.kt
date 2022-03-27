package com.example.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Directions for the scheduler screen.
 */
object SchedulerDirections {

    /**
     * Default empty direction.
     */
    val Default = object : NavigationCommand {
        override val args: List<NamedNavArgument> = emptyList()
        override val destination: String = ""
    }

    /**
     * Root of the graph navigation.
     */
    val root = object : NavigationCommand {
        override val args: List<NamedNavArgument> = emptyList()
        override val destination: String = "root"
    }

    /**
     * Route to the offices composable.
     */
    val offices = object : NavigationCommand {
        override val args: List<NamedNavArgument> = emptyList()
        override val destination: String = "offices"
    }

    /**
     * Route to the scheduler composable.
     */
    val scheduler = object : NavigationCommand {
        override val args: List<NamedNavArgument> = listOf(
              navArgument("spaceId") {
                    type = NavType.IntType
              }
        )
        override val destination: String = "scheduler/{spaceId}"
    }
}
