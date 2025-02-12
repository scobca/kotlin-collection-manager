package org.example.kotlincollectionmanager.command.intefaces

interface Command<T : Validator> {
    val validator: T
    val name: String
    val description: String
    val keys: List<String>?

    fun execute(vararg args: String?)
    fun validate(arg: List<String>)
}