package org.example.kotlincollectionmanager.command.validators

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.springframework.stereotype.Component

/**
 * Validator for commands that do not accept arguments.
 * Checks for the absence of arguments and executes the command if there are no arguments.
 */
@Component
class NoArgsCommandValidator : Validator {
    /**
     * Checks the list of command arguments and executes the command if there are no arguments.
     *
     * @param args List of command arguments.
     * @param command is the command for which the arguments are checked.
     */
    override fun validateArgs(args: List<String>, command: Command<out Validator>) {
        return if (args.isEmpty()) {
            command.execute()
        } else {
            println("Unexpected arguments: ${args.joinToString(" ")}. For more details enter 'help' in command line")
        }
    }
}