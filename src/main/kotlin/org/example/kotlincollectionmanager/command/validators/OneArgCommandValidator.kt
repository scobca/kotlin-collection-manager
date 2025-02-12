package org.example.kotlincollectionmanager.command.validators

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.intefaces.Validator

class OneArgCommandValidator : Validator {
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