package org.example.kotlincollectionmanager.command.intefaces

interface AutoCommand<T : Validator> : Command<T> {
    fun autoExecute(vararg args: String?)
}