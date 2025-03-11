package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

/**
 * Command for filtering apartments by the presence of a substring in the name.
 * Implements the Command interface and uses the OneArgCommandValidator validator to check for a single substring argument to search for.
 */
@Component
class FilterContainsNameCommand(
    /**
     * Validator for checking the presence of a single argument.
     */
    override val validator: OneArgCommandValidator,
    /**
     * A service for interacting with a collection of apartments.
     */
    private val receiverService: ReceiverService
) : Command<OneArgCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "filter_contains_name"

    /**
     * Description of the command.
     */
    override val description: String = "Return all flats, which name contains similar word from key"

    /**
     * The list of keywords for the team is ["name"], indicating that the team expects a name to be searched for.
     */
    override val keys: List<String>? = listOf("name")

    /**
     * Executes the command by calling the filterContainsName method of the ReceiverService with the passed substring.
     *
     * @param args Command arguments are a substring for searching.
     */
    override fun execute(vararg args: String?) {
        val sub = args.elementAt(0)

        if (sub == null) {
            println("Incorrect input. Please try again")
        } else {
            receiverService.filterContainsName(sub)
        }
    }

    /**
     * Checks the arguments of the command using the OneArgCommandValidator validator.
     *
     * @param args List of command arguments.
     */
    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}