package com.example.navigation

import androidx.navigation.NamedNavArgument

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
     * Route to scheduler composable.
     */
    val scheduler = object : NavigationCommand {
        override val args: List<NamedNavArgument> = emptyList()
        override val destination: String = "scheduler"
    }
}
