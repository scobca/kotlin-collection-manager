package org.example.kotlincollectionmanager.command.storage

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommandsHistory {
    val history: LinkedList<String> = LinkedList()

    fun getCommandsHistory(): LinkedList<String> = history

    fun addCommand(command: Command<out Validator>) {
        if (history.size >= 10) {
            history.removeFirst()
        }
        history.add(command.name)
    }
}