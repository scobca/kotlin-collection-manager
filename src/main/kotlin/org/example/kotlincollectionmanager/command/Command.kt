package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.intefaces.Command

abstract class Command : Command {
    abstract val name: String
    abstract val description: String
    abstract val keys: Set<String>

    abstract override fun execute(vararg args: String)

    override fun describe(): String {
        return this.description
    }
}