package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * Command to display information about the flat collection.
 * Implements the Command interface and uses the NoArgsCommandValidator to check for missing arguments.
 */
@Component
class InfoCommand(
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
    override val name: String = "info"

    /**
     * Description of the command.
     */
    override val description: String = "Display information about the collection"

    /**
     * The list of keywords for the command is null, because the command doesn't require arguments.
     */
    override val keys: List<String>? = null

    /**
     * Executes the command by calling the info method of the ReceiverService service.
     *
     * @param args Command arguments (not used).
     */
    override fun execute(vararg args: String?) {
        receiverService.info()
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