package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.HelpCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.example.kotlincollectionmanager.utils.Logger.describeCommand
import org.example.kotlincollectionmanager.utils.Logger.printHeader
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class HelpCommand(override val validator: HelpCommandValidator, @Lazy private val invokerService: InvokerService) :
    Command<HelpCommandValidator> {
    override val name: String = "help"
    override val description: String = "Displays help for available commands | for a specific command."
    override val keys: List<String>? = null

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

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}