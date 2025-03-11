package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

/**
 * Command to delete all apartments by the balcony parameter.
 * Implements the Command interface and uses OneArgCommandValidator to validate arguments.
 */
@Component
class RemoveAllByBalconyCommand(
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
    override val name: String = "remove_by_balcony"

    /**
     * Description of the command.
     */
    override val description: String = "Removes all flats by balcony parameter"

    /**
     * A list of keywords for the command containing ["true | false"], which indicates that the command expects an argument of type "true" or "false".
     */
    override val keys: List<String>? = listOf("true | false")

    /**
     * Executes the apartment deletion command using the specified argument.
     *
     * @param args array of command arguments. One String argument is expected ("true" or "false").
     */
    override fun execute(vararg args: String?) {
        val bool = args.elementAt(0)

        if (bool.equals("true", ignoreCase = true) || bool.equals("false", ignoreCase = true)) {
            if (bool != null) {
                receiverService.removeAllByBalcony(bool)
            }
        } else {
            println("Unknown command arguments. Please try again later")
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