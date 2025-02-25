package org.example.kotlincollectionmanager.utils

import org.example.kotlincollectionmanager.command.InsertCommand
import org.example.kotlincollectionmanager.command.UpdateCommand
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommandsErrorHandler(
    private val receiverService: ReceiverService,
    @Lazy private val insertCommand: InsertCommand,
    @Lazy private val updateCommand: UpdateCommand
) {
    private val scanner = Scanner(System.`in`)

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

    fun resolveFlatUpdateError(id: Long) {
        println("Flat with id: $id doesn't exists. Want to create it? (yes | no)")
        print("> ")
        val response = scanner.nextLine()

        if (response.equals("yes", ignoreCase = true)) {
            insertCommand.execute(id.toString())
        }
    }
}