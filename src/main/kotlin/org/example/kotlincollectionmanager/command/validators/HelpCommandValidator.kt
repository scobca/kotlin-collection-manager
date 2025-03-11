package org.example.kotlincollectionmanager.command.validators

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.springframework.stereotype.Component

/**
 * Validator for the help command.
 * Inherits functionality from NoArgsCommandValidator and provides specific logic for processing arguments to the help command.
 */
@Component
class HelpCommandValidator : NoArgsCommandValidator() {
    /**
     * Checks the arguments of the help command and executes the command depending on the transmitted data.
     *
     * @param args List of command arguments.
     * @param command Is an instance of the command that called the method
     */
    override fun validateArgs(args: List<String>, command: Command<out Validator>) {
        if (args.isEmpty()) {
            command.execute()
        } else {
            if (args.size == 1) {
                command.execute(args[0].trim())
            } else {
                println("Unexpected arguments: ${args.joinToString(" ")}. For more details enter 'help' in command line")
            }
        }
    }
}