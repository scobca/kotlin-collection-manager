package org.example.kotlincollectionmanager.utils

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.intefaces.Validator

object Logger {
    fun describeCommand(command: Command<out Validator>) {
        println(
            String.format(
                "| %-30s | %-30s | %-30s |",
                command.name,
                command.keys,
                command.description
            )
        )
    }

    fun printHeader() {
        println(
            String.format(
                "| %-30s | %-30s | %-30s |",
                "<command>",
                "<keys>",
                "<description>"
            )
        )
        println("—".repeat(100))
    }
}