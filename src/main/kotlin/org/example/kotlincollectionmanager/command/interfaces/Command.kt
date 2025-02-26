package org.example.kotlincollectionmanager.command.interfaces

interface Command<T : Validator> {
    val validator: T
    val name: String
    val description: String
    val keys: List<String>?

    fun execute(vararg args: String?)
    fun validate(args: List<String>)
}