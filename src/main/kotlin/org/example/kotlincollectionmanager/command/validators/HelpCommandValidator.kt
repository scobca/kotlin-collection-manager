package org.example.kotlincollectionmanager.command.validators

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.springframework.stereotype.Component

@Component
class HelpCommandValidator : NoArgsCommandValidator() {
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