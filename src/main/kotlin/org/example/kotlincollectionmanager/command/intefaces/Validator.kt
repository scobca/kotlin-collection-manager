package org.example.kotlincollectionmanager.command.intefaces

interface Validator {
    fun validateArgs(args: List<String>, command: Command<out Validator>)
}