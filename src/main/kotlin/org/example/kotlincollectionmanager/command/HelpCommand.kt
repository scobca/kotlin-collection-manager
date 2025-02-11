package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.invoker.InvokerService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class HelpCommand(@Lazy private val invokerService: InvokerService) : Command() {
    override val name: String = "help"
    override val description: String = "Shows information about commands"
    override val keys: Set<String> = setOf("Command")

    override fun execute(vararg args: String) {
        val commands: Map<String, Command> = invokerService.getCommands()
        val delimiter = "————————————————————————————————————"
        val format = "%-20s %-25s %s"

        if (args.isEmpty()) {
            println("Доступные команды:")
            println(delimiter)
            println(String.format(format, "Команда", "Ключи", "Описание"))
            commands.forEach { (name, command) ->
                println(String.format(format, name, command.keys, command.description))
            }
            println(delimiter)
        } else {
            val argsList: List<String> = args[0].split(" ")

            if (argsList.size == 1) {
                val commandName = args[0]
                val command = commands[commandName]

                if (command != null) {
                    println("Команда '${command.name}':")
                    println(delimiter)
                    println(String.format(format, "Команда", "Ключи", "Описание"))
                    println(String.format(format, name, command.keys, command.description))
                    println(delimiter)
                } else {
                    println("Command $commandName not found")
                }
            } else {
                println(
                    "Ошибка в выполнении команды: неизвестный аргумент ${
                        argsList.joinToString(" ")
                    }"
                )
            }
        }
    }
}