package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.parser.JsonParser
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

/**
 * Command to save apartments to a JSON file.
 * Implements the Command interface and uses OneArgCommandValidator to validate arguments.
 */
@Component
class SaveCommand(
    /**
     * Validator for checking the presence of a single argument.
     */
    override val validator: OneArgCommandValidator,
    /**
     * A parser for working with JSON.
     */
    private val parser: JsonParser,
    /**
     * A service for interacting with the recipient of the data.
     */
    private val receiverService: ReceiverService
) : Command<OneArgCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "save"

    /**
     * Description of the command.
     */
    override val description: String = "Saves flats to JSON file"

    /**
     * A keyword list for the command containing ["filename"], which indicates that the command expects a file name.
     */
    override val keys: List<String>? = listOf("filename")

    /**
     * Executes the command to save apartments to a JSON file.
     *
     * @param args array of command arguments. A single String argument is expected, representing the file name.
     */
    override fun execute(vararg args: String?) {
        val filename = args.elementAt(0)

        if (filename != null) {
            val flats = receiverService.getFlats()

            parser.saveFlats(flats, filename)
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