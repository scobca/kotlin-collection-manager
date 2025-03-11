package org.example.kotlincollectionmanager.utils

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator

/**
 * An object for displaying information about commands in the console.
 * Provides methods for formatted output of the command name, keys, and description.
 */
object Logger {
    /**
     * Displays information about the command in formatted form.
     * Uses the command fields: name, keys, description.
     *
     * @param command is the command that needs to be displayed.
     */
    fun describeCommand(command: Command<out Validator>) {
        println(
            String.format(
                "| %-30s | %-30s | %-30s |",
                command.name,
                command.keys,
                command.description
            )
        )
    }

    /**
     * Displays the table header with column names.
     * It is used to visually separate the title from the content.
     */
    fun printHeader() {
        println(
            String.format(
                "| %-30s | %-30s | %-30s |",
                "<command>",
                "<keys>",
                "<description>"
            )
        )
        println("—".repeat(100))
    }
}