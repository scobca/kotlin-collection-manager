package org.example.kotlincollectionmanager.invoker

import jakarta.annotation.PostConstruct
import org.example.kotlincollectionmanager.command.Command
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class InvokerService(@Autowired private val commands: List<Command>) {
    private val commandMap = mutableMapOf<String, Command>()

    @PostConstruct // Выполняется после создания бина
    fun init() {
        commands.forEach { command ->
            commandMap[command.name] = command // Заполняем map командами
        }
    }

    fun run() {
        val scanner = Scanner(System.`in`)

        println("Введите команду:")
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            val tokens = line.split(" ")
            val command = commandMap[tokens[0]]

            if (command != null) {
                command.execute()
            } else {
                println("Неизвестная команда: ${tokens[0]}")
            }
            println("Введите команду:")
        }
    }
}