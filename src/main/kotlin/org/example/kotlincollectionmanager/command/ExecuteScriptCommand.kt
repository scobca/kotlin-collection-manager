package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.io.File

/**
 * Command to execute a script from a file.
 * Implements the Command interface and uses the OneArgCommandValidator to check for a single argument, the file path.
 */
@Component
class ExecuteScriptCommand(
    /**
     * Validator for checking the presence of a single argument.
     */
    override val validator: OneArgCommandValidator,
    /**
     * A service for interacting with commands and the scanner.
     */
    @Lazy private val invokerService: InvokerService,
) : Command<OneArgCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "execute"

    /**
     * Description of the command.
     */
    override val description: String = "Execute a script from a file"

    /**
     * The keyword list for the command is ["filepath"], indicating that the command expects a file path.
     */
    override val keys: List<String>? = listOf("filepath")

    /**
     * Executes the command by reading the file at the specified path and executing commands from it.
     *
     * @param args Command arguments are the path to the file.
     */
    override fun execute(vararg args: String?) {
        val fileName = args[0]?.trim()
        val resource = File("$fileName")
        var lifetime = true

        if (resource.exists()) {
            val commands = invokerService.getCommands()

            if (resource.length() > 0) {
                resource.forEachLine { commandLine ->
                    run {
                        if (lifetime) {
                            val line = commandLine.trim()
                            val command = commands[line.split(" ")[0]]
                            val commandArgs = line.split(" ").drop(1)

                            if (command != null) {
                                if (command.name == "exit") {
                                    this.invokerService.closeScanner()
                                    lifetime = false
                                }
                                command.validate(commandArgs)
                            } else {
                                println("Command with name ${line.split(" ")[0]} not found")
                            }
                        }
                    }
                }
            } else {
                println("Error occurred while executing script. File is empty.")
            }
        } else {
            println("Error occurred. File $fileName doesn't exist.")
        }
    }

    /**
     * Checks the arguments of the command using the OneArgCommandValidator validator.
     *
     * @param args List of command arguments.
     */
    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}