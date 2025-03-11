package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

/**
 * Command to delete all flats with an ID below the specified argument.
 * Implements the Command interface and uses OneArgCommandValidator to validate arguments.
 */
@Component
class RemoveLowerKeyCommand(
    /**
     * A service for interacting with the recipient of the data.
     */
    private val receiverService: ReceiverService,
    /**
     * Validator for checking the presence of a single argument.
     */
    override val validator: OneArgCommandValidator
) : Command<OneArgCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "remove_lower_key"
    /**
     * Description of the command.
     */
    override val description: String = "Remove all flats with id lower than arguments id"
    /**
     * A list of keywords for the command containing ["id"], which indicates that the command is expecting an ID.
     */
    override val keys: List<String>? = listOf("id")

    /**
     * Executes the command to delete flats with the ID below the specified argument.
     *
     * @param args array of command arguments. A single String argument is expected, representing the identifier.
     */
    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLong()

        if (id == null) {
            println("Id format is invalid. Please try again")
        } else {
            receiverService.removeLowerKey(id)
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