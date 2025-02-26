package org.example.kotlincollectionmanager.command.interfaces

interface AutoCommand<T : Validator> : Command<T> {
    fun autoExecute(vararg args: String?)
}