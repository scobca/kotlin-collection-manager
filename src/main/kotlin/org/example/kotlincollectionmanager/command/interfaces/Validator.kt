package org.example.kotlincollectionmanager.command.interfaces

/**
 * Interface for validating command arguments.
 * Defines a contract for verifying the correctness of the input data before executing the command.
 */
interface Validator {

    /**
     * Checks the arguments of the command for compliance with the requirements.
     *
     * @param args List of arguments for validation.
     * @param command is the command for which the arguments are checked.
     */
    fun validateArgs(args: List<String>, command: Command<out Validator>)
}