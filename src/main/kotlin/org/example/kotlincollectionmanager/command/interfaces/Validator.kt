package org.example.kotlincollectionmanager.command.interfaces

interface Validator {
    fun validateArgs(args: List<String>, command: Command<out Validator>)
}