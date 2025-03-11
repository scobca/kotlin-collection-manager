package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

/**
 * Command to shut down the application without saving data.
 * Implements the Command interface and uses the NoArgsCommandValidator to check for missing arguments.
 */
@Component
class ExitCommand(
    /**
     * Validator for checking the absence of arguments.
     */
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    /**
     * A service for interacting with the scanner.
     */
    @Lazy private val invokerService: InvokerService
) :
    Command<NoArgsCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "exit"

    /**
     * The list of keywords for the command is null, because the command doesn't require arguments.
     */
    override val description: String = "Program termination (without saving to a file)"

    /**
     * The list of keywords for the command is null, because the command doesn't require arguments.
     */
    override val keys: List<String>? = null

    /**
     * Executes the command, displaying an exit message and closing the scanner.
     *
     * @param args Command arguments (not used).
     */
    override fun execute(vararg args: String?) {
        println("Exiting...")
        invokerService.closeScanner()
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