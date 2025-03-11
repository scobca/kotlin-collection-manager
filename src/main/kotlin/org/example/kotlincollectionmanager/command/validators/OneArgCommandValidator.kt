package org.example.kotlincollectionmanager.command.validators

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.springframework.stereotype.Component

/**
 * Validator for commands that take exactly one argument.
 * Checks for one argument and executes the command if there is one argument.
 */
@Component
class OneArgCommandValidator : Validator {
    /**
     * Checks the list of command arguments and executes the command if there is only one argument.
     *
     * @param args List of command arguments.
     * @param command is the command for which the arguments are checked.
     */
    override fun validateArgs(args: List<String>, command: Command<out Validator>) {
        if (args.isEmpty()) {
            println("Arguments for this command cannot be empty. For more info write 'help <command>' into console")
        } else if (args.size == 1) {
            command.execute(args[0].trim())
        } else {
            println("Unexpected arguments: ${args.joinToString(" ")}. For more details enter 'help' in command line")
        }
    }
}