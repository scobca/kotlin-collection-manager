package org.example.kotlincollectionmanager.utils

import org.example.kotlincollectionmanager.command.InsertCommand
import org.example.kotlincollectionmanager.command.UpdateCommand
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.util.*

/**
 * A component for error handling that occurs when executing commands to insert and update objects.
 * Allows the user to choose further actions depending on the problem encountered.
 */
@Component
class CommandsErrorHandler(
    private val receiverService: ReceiverService,
    @Lazy private val insertCommand: InsertCommand,
    @Lazy private val updateCommand: UpdateCommand
) {
    /**
     * A scanner for reading user input from the console.
     */
    private val scanner = Scanner(System.`in`)

    /**
     * Handles an error when an object with the specified ID already exists during an insertion attempt.
     * Prompts the user for confirmation to delete an existing object and execute the insert command.
     *
     * @param id Is the ID of an object that already exists.
     */
    fun resolveFlatInsertError(id: Long) {
        println("Flat with id: $id already exists. Want to remove it? (yes | no)")
        print("> ")
        val response = scanner.nextLine()

        if (response.equals("yes", ignoreCase = true)) {
            receiverService.remove(id)
            insertCommand.execute(id.toString())
        } else {
            updateCommand.execute(id.toString())
        }
    }

    /**
     * Handles an error when an object with the specified ID does not exist during an update attempt.
     * Requests confirmation from the user to create a new object and execute the insert command.
     *
     * @param id Is the ID of an object that does not exist.
     */
    fun resolveFlatUpdateError(id: Long) {
        println("Flat with id: $id doesn't exists. Want to create it? (yes | no)")
        print("> ")
        val response = scanner.nextLine()

        if (response.equals("yes", ignoreCase = true)) {
            insertCommand.execute(id.toString())
        }
    }
}