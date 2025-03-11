package org.example.kotlincollectionmanager.command.interfaces

/**
 * Interface for commands that can be executed either manually or automatically.
 * Extends the Command interface and adds a method for automatic execution.
 *
 * @param <T> is the type of validator used by the team.
 */
interface AutoCommand<T : Validator> : Command<T> {

    /**
     * Executes the command automatically with arguments passed.
     *
     * @param args Command line arguments.
     */
    fun autoExecute(vararg args: String?)
}