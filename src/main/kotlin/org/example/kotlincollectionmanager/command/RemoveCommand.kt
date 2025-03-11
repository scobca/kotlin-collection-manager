package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

/**
 * Command to delete an item by its ID.
 * Implements the Command interface and uses OneArgCommandValidator to validate arguments.
 */
@Component
class RemoveCommand(
    /**
     * Validator for checking the presence of a single argument.
     */
    override val validator: OneArgCommandValidator,
    /**
     * A service for interacting with the recipient of the data.
     */
    private val receiverService: ReceiverService,
) : Command<OneArgCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "remove"
    /**
     * Description of the command.
     */
    override val description: String = "Removes an element by it Id"
    /**
     * A keyword list for the command containing ["id"], which indicates that the command is expecting an item ID.
     */
    override val keys: List<String> = listOf("id")

    /**
     * Executes the command to delete an element by the specified identifier.
     *
     * @param args array of command arguments. A single String argument is expected, representing the element identifier.
     */
    override fun execute(vararg args: String?) {
        if (args[0]?.toLongOrNull() == null) {
            println("Id format is invalid. Please try again")
        } else {
            args[0]?.toLong()?.let { this.receiverService.remove(it) }
        }
    }

    /**
     * Checks the validity of the command arguments.
     *
     * @param args list of arguments to check.
     */
    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}