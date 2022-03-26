package com.example.navigation

import androidx.navigation.NamedNavArgument

/**
 * Defines a command to navigate somewhere. Used for navigation between
 * composable screens.
 */
interface NavigationCommand {

    /**
     * List of named arguments.
     */
    val args: List<NamedNavArgument>

    /**
     * Link to the destination.
     */
    val destination: String
}
