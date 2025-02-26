package org.example.kotlincollectionmanager.command.validators

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.springframework.stereotype.Component

@Component
class NoArgsCommandValidator : Validator {
    override fun validateArgs(args: List<String>, command: Command<out Validator>) {
        return if (args.isEmpty()) {
            command.execute()
        } else {
            println("Unexpected arguments: ${args.joinToString(" ")}. For more details enter 'help' in command line")
        }
    }
}