package org.example.kotlincollectionmanager.invoker

import jakarta.annotation.PostConstruct
import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.example.kotlincollectionmanager.command.storage.CommandsHistory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class InvokerService(@Autowired private val commandsList: List<Command<out Validator>>) {
    private val commands = mutableMapOf<String, Command<out Validator>>()
    private val history = CommandsHistory()
    private var runtime = true


    @PostConstruct
    fun init() {
        commandsList.forEach { command ->
            commands[command.name] = command
        }
    }

    fun getCommands(): Map<String, Command<out Validator>> = commands
    fun getCommandsHistory(): LinkedList<String> = history.getCommandsHistory()

    fun closeScanner() {
        runtime = false
    }

    fun run() {
        val scanner = Scanner(System.`in`)

        print("> ")
        while (runtime) {
            val line = scanner.nextLine().trim()
            val command = commands[line.split(" ")[0]]
            val args = line.split(" ").drop(1)

            if (line == "") {
                print("> ")
                continue
            }

//            if (command?.name == "exit") {
//                break
//            }

            if (command != null) {
                command.validate(args)
                history.addCommand(command)
            } else {
                println("Command with name ${line.split(" ")[0]} not found")
            }

            print("> ")
        }
    }
}