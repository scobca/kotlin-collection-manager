package org.example.kotlincollectionmanager.command.storage

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.springframework.stereotype.Component
import java.util.*

/**
 * A component for storing the history of executed commands.
 * Uses a linked list to store the names of commands in the order they are executed.
 */
@Component
class CommandsHistory {
    /**
     * A linked list of strings containing the names of executed commands.
     */
    val history: LinkedList<String> = LinkedList()

    /**
     * Returns the entire history of executed commands.
     *
     * @return is a linked list of command names.
     */
    fun getCommandsHistory(): LinkedList<String> = history

    /**
     * Adds The name of the command to the history.
     * If the history already contains 10 commands, delete the oldest command before adding a new one.
     *
     * @param command is the command whose name needs to be added to the history.
     */
    fun addCommand(command: Command<out Validator>) {
        if (history.size >= 10) {
            history.removeFirst()
        }
        history.add(command.name)
    }
}