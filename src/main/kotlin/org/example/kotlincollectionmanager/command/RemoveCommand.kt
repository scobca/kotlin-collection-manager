package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

@Component
class RemoveCommand(
    override val validator: OneArgCommandValidator,
    private val receiverService: ReceiverService,
) : Command<OneArgCommandValidator> {
    override val name: String = "remove"
    override val description: String = "Removes an element by it Id"
    override val keys: List<String> = listOf("id")

    override fun execute(vararg args: String?) {
        if (args[0]?.toLongOrNull() == null) {
            println("Id format is invalid. Please try again")
        } else {
            args[0]?.toLong()?.let { this.receiverService.remove(it) }
        }
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}