package org.example.kotlincollectionmanager.command.intefaces

interface Command {
    fun execute(vararg args: String)
    fun describe() : String
}