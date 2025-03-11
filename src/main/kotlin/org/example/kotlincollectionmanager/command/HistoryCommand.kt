package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

/**
 * Command to displaying the last 10 commands executed in the application.
 * Implements the Command interface and uses the NoArgsCommandValidator to check for missing arguments.
 */
@Component
class HistoryCommand(
    /**
     * Validator for checking the absence of arguments.
     */
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    /**
     * A service for interacting with the history of teams.
     */
    @Lazy private val invokerService: InvokerService
) : Command<NoArgsCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "history"

    /**
     * Description of the command.
     */
    override val description: String = "Return last 10 commands from cli"

    /**
     * The list of keywords for the command is null, because the command does not require arguments.
     */
    override val keys: List<String>? = null

    /**
     * Executes the command, displaying a list of recent commands.
     *
     * @param args Command arguments (not used).
     */
    override fun execute(vararg args: String?) {
        println("Recent commands list:")
        invokerService.getCommandsHistory().forEach { command -> println("——> $command") }
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