package org.example.kotlincollectionmanager.command.validators

import org.example.kotlincollectionmanager.command.interfaces.AutoCommand
import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.springframework.stereotype.Component

/**
 * Validator for commands that can take either one or two arguments.
 * Checks for one or two arguments and executes the command depending on the number of arguments.
 */
@Component
class OneOrTwoArgsValidator : Validator {
    /**
     * An internal method for checking arguments and executing a command with one or two arguments.
     *
     * @param args List of command arguments.
     * @param command is the command for which the arguments are checked.
     */
    private fun validateTwoArgs(args: List<String>, command: AutoCommand<out Validator>) {
        if (args.isEmpty()) {
            println("Arguments for this command cannot be empty. For more info write 'help <command>' into console")
        } else if (args.size == 1) {
            command.execute(args[0].trim())
        } else if (args.size == 2) {
            command.autoExecute(args[0].trim(), args[1].trim())
        } else {
            println("Unexpected arguments: ${args.joinToString(" ")}. For more details enter 'help' in command line")
        }
    }

    /**
     * The main method for verifying command arguments.
     * If the command implements the AutoCommand interface, it calls the validateTwoArgs method to verify and execute the command.
     *
     * @param args List of command arguments.
     * @param command is the command for which the arguments are checked.
     */
    override fun validateArgs(args: List<String>, command: Command<out Validator>) {
        if (command is AutoCommand) {
            validateTwoArgs(args, command)
        }
    }
}