package org.example.kotlincollectionmanager.command.interfaces

/**
 * Interface for commands that can be executed in the application.
Defines properties for identifying and describing the command, as well as methods for executing and validating the command.
 *
 * @param <T> is the type of validator used by the team.
 */
interface Command<T : Validator> {
    /**
     * Validator used to validate command arguments.
     */
    val validator: T

    /**
     * The name of the command.
     */
    val name: String

    /**
     * Description of the command.
     */
    val description: String

    /**
     * The list of arguments that the command expects.
     */
    val keys: List<String>?

    /**
     * Executes a command with arguments passed.
     *
     * @param args Command line arguments.
     */
    fun execute(vararg args: String?)

    /**
     * Checks the arguments of the command using the validator.
     *
     * @param args Arguments to check.
     */
    fun validate(args: List<String>)
}