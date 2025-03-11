package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.AutoCommand
import org.example.kotlincollectionmanager.command.validators.OneOrTwoArgsValidator
import org.example.kotlincollectionmanager.strategies.ReplaceIfLowerStrategy
import org.example.kotlincollectionmanager.utils.StringToFlatDataConverter
import org.springframework.stereotype.Component

/**
 * Command to replace the flat in the collection if the new flat is better.
 * Implements the AutoCommand interface and uses OneOrTwoArgsValidator to validate arguments.
 */
@Component
class ReplaceIfLowerCommand(
    /**
     * Validator for checking the presence of one or two arguments.
     */
    override val validator: OneOrTwoArgsValidator,
    /**
     * A strategy for replacing an flat if it is better.
     */
    private val replaceIfLowerStrategy: ReplaceIfLowerStrategy,
    /**
     * String converter to flat data.
     */
    private val flatDataConverter: StringToFlatDataConverter
) : AutoCommand<OneOrTwoArgsValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "replace_if_lower"

    /**
     * Description of the command.
     */
    override val description: String = "Replace a flat from a collection if it lower than new flat"
    /**
     * A list of keywords for the command containing ["id"], which indicates that the command is expecting an ID.
     */
    override val keys: List<String> = listOf("id")

    /**
     * Executes the flat replacement command using the specified identifier.
     *
     * @param args array of command arguments. A single String argument is expected, representing the identifier.
     */
    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLong()

        replaceIfLowerStrategy.lifeTimeExecution(id)
    }

    /**
     * Executes an automatic flat replacement command based on the specified arguments.
     *
     * @param args array of command arguments. Two arguments are expected: the identifier and the flat data.
     */
    override fun autoExecute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()
        val data = args.elementAt(1)

        replaceIfLowerStrategy.automaticallyExecution(id, flatDataConverter.convertStringToFlatData(data.toString()))
    }

    /**
     * Checks the validity of the command arguments.
     *
     * @param args list of arguments to check.
     */
    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}