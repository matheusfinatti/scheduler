package com.example.navigation

import androidx.navigation.NamedNavArgument
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Manages navigation between composables.
 */
class NavigationManager {

    /**
     * Flow that emits navigation directions.
     */
    val commands = MutableStateFlow(SchedulerDirections.Default)

    /**
     * Navigates to a new direction.
     *
     * @param direction a command to a new destination.
     */
    fun navigate(direction: NavigationCommand) {
        commands.value = direction
    }

    /**
     * Navigates to a new direction with arguments.
     *
     * @param direction a command to a new destination.
     * @param args a list of arguments to be passed.
     */
    fun navigate(direction: NavigationCommand, vararg args: String) {
        val destination = args.foldIndexed(direction.destination) { i, str, arg ->
            str.replace("{${direction.args[i].name}}", arg)
        }

        val newCommand = object : NavigationCommand {
            override val args: List<NamedNavArgument> = direction.args
            override val destination: String = destination
        }

        commands.value = newCommand
    }
}
