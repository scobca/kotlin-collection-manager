package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.io.File

@Component
class ExecuteScriptCommand(
    override val validator: OneArgCommandValidator,
    @Lazy private val invokerService: InvokerService,
) : Command<OneArgCommandValidator> {
    override val name: String = "execute"
    override val description: String = "Execute a script from a file"
    override val keys: List<String>? = listOf("filepath")

    override fun execute(vararg args: String?) {
        val fileName = args[0]?.trim()
        val resource = File("$fileName")
        var lifetime = true

        if (resource.exists()) {
            val commands = invokerService.getCommands()

            if (resource.length() > 0) {
                resource.forEachLine { commandLine -> run {
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
                } }
            } else {
                println("Error occurred while executing script. File is empty.")
            }
        } else {
            println("Error occurred. File $fileName doesn't exist.")
        }
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}