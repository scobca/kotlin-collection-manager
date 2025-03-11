package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * Command to output all collection items as a string.
 * Implements the Command interface and uses the NoArgsCommandValidator to validate the absence of arguments.
 */
@Component
class ShowCommand(
    /**
     * Validator for checking the absence of arguments.
     */
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    /**
     * A service for interacting with the recipient of the data.
     */
    private val receiverService: ReceiverService
) : Command<NoArgsCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "show"

    /**
     * Description of the command.
     */
    override val description: String = "Outputs all the elements of the collection in a string representation"

    /**
     * The list of keywords for the command is null, because the command doesn't require arguments.
     */
    override val keys: List<String>? = null

    /**
     * Executes the command to output the collection items.
     *
     * @param args array of command arguments. No arguments are expected.
     */
    override fun execute(vararg args: String?) {
        receiverService.show()
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