package com.example.navigation

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
}
