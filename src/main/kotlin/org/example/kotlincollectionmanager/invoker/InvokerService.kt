package org.example.kotlincollectionmanager.invoker

import jakarta.annotation.PostConstruct
import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.intefaces.Validator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class InvokerService(@Autowired private val commandsList: List<Command<out Validator>>) {
    private val commands = mutableMapOf<String, Command<out Validator>>()

    @PostConstruct
    fun init() {
        commandsList.forEach { command ->
            commands[command.name] = command
        }
    }

    fun getCommands(): Map<String, Command<out Validator>> = commands

    fun run() {
        val scanner = Scanner(System.`in`)

        println("Введите команду:")
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine().trim()
            val command = commands[line.split(" ")[0]]
            val args = line.split(" ").drop(1)

            if (command != null) {
                command.validate(args)
            } else {
                println("Command with name ${line.split(" ")[0]} not found")
            }
        }
    }
}