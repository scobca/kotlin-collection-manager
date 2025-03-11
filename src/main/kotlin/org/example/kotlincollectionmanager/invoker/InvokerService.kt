package org.example.kotlincollectionmanager.invoker

import jakarta.annotation.PostConstruct
import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.interfaces.Validator
import org.example.kotlincollectionmanager.command.storage.CommandsHistory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

/**
 * A service for executing commands and managing their history.
 * Implements the logic of reading commands from the console and executing them.
 */
@Component
class InvokerService(
    /**
     * List of available commands.
     */
    @Autowired private val commandsList: List<Command<out Validator>>
) {
    /**
     * A command map where the key is the command name.
     */
    private val commands = mutableMapOf<String, Command<out Validator>>()
    /**
     * History of executed commands.
     */
    private val history = CommandsHistory()
    /**
     * Flag indicating that the service should continue to work.
     */
    private var runtime = true

    /**
     * Initializes the command map after creating the bean.
     */
    @PostConstruct
    fun init() {
        commandsList.forEach { command ->
            commands[command.name] = command
        }
    }

    /**
     * Returns a map of available commands.
     *
     * @return is a command map where the key is the command name.
     */
    fun getCommands(): Map<String, Command<out Validator>> = commands

    /**
     * Returns the history of executed commands.
     *
     * @return command history in the form of a linked list.
     */
    fun getCommandsHistory(): LinkedList<String> = history.getCommandsHistory()

    /**
     * Stops the service.
     */
    fun closeScanner() {
        runtime = false
    }

    /**
     * Starts a cycle of reading and executing commands from the console.
     */
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