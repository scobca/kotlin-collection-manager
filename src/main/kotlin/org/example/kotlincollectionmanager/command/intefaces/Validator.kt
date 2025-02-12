package org.example.kotlincollectionmanager.command.intefaces

interface Validator {
    fun validate(args: List<String>): String
}