package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.example.kotlincollectionmanager.utils.Logger.describeCommand
import org.example.kotlincollectionmanager.utils.Logger.printHeader
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class HelpCommand(override val validator: NoArgsCommandValidator, @Lazy private val invokerService: InvokerService) :
    Command<NoArgsCommandValidator> {
    override val name: String = "help"
    override val description: String = "help command"
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

    override fun validate(arg: List<String>) {
        val response = validator.validate(arg)

        if (response == "OK") {
            execute()
        } else {
            if (arg.size == 1) {
                execute(arg[0])
            } else {
                println("Unexpected arguments: ${arg.joinToString(" ")}. For more details enter 'help' in command line")
            }
        }
    }
}