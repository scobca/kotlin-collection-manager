package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.HelpCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.example.kotlincollectionmanager.utils.Logger.describeCommand
import org.example.kotlincollectionmanager.utils.Logger.printHeader
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

/**
 * Command to output help for available commands or for a specific command.
 * Implements the Command interface and uses the HelpCommandValidator to validate arguments.
 */
@Component
class HelpCommand(
    /**
     * Validator for validating command arguments.
     */
    override val validator: HelpCommandValidator,
    /**
     * A service for interacting with commands.
     */
    @Lazy private val invokerService: InvokerService
) :
    Command<HelpCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "help"

    /**
     * Description of the command.
     */
    override val description: String = "Displays help for available commands | for a specific command."

    /**
     * The list of keywords for the command is null, because the command doesn't require arguments.
     */
    override val keys: List<String>? = null

    /**
     * Executes the command, displaying the help for commands.
     *
     * @param args Command arguments — the name of the command for detailed information.
     */
    override fun execute(vararg args: String?) {
        val commands = invokerService.getCommands()

        if (args.isEmpty()) {
            printHeader()
            commands.forEach {
                describeCommand(it.value)
            }
        } else {
            if (commands[args[0]] != null) {
                printHeader()
                commands[args[0]]?.let { describeCommand(it) }
            } else {
                println("Command with name ${args[0]} not found")
            }
        }
    }

    /**
     * Checks the command arguments using the HelpCommandValidator.
     *
     * @param args List of command arguments.
     */
    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}