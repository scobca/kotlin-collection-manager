package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * Command to clear the flat collection.
 * Implements the Command interface and uses the NoArgsCommandValidator to check for missing arguments.
 */
@Component
class ClearCommand(
    /**
     * Validator for checking the absence of arguments.
     */
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    /**
     * A service for interacting with a collection of flats.
     */
    private val receiverService: ReceiverService
) : Command<NoArgsCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "clear"

    /**
     * Description of the command.
     */
    override val description: String = "Clears the flats collection"

    /**
     * The list of keywords for the command is null, because the command doesn't require arguments.
     */
    override val keys: List<String>? = null

    /**
     * Executes the command by calling the clear method of the Receivservice, and displays a message about successful cleanup.
     *
     * @param args Command arguments (not used).
     */
    override fun execute(vararg args: String?) {
        receiverService.clear()
        println("Collection cleared successfully")
    }

    /**
     * Checks the command arguments using the NoArgsCommandValidator.
     *
     * @param args List of command arguments.
     */
    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }

}