package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.AutoCommand
import org.example.kotlincollectionmanager.command.validators.OneOrTwoArgsValidator
import org.example.kotlincollectionmanager.strategies.UpdateFlatStrategy
import org.example.kotlincollectionmanager.utils.StringToFlatDataConverter
import org.springframework.stereotype.Component

/**
 * Command to update the apartment by its ID.
 * Implements the AutoCommand interface and uses OneOrTwoArgsValidator to validate arguments.
 */
@Component
class UpdateCommand(
    /**
     * Validator for checking the presence of one or two arguments.
     */
    override val validator: OneOrTwoArgsValidator,
    /**
     * Strategy for apartment renovation.
     */
    private val updateFlatStrategy: UpdateFlatStrategy,
    /**
     * String converter to apartment data.
     */
    private val flatDataConverter: StringToFlatDataConverter,
) :
    AutoCommand<OneOrTwoArgsValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "update"
    /**
     * Description of the command.
     */
    override val description: String = "Updates the command by it's Id"
    /**
     * A list of keywords for the team containing ["id"], which indicates that the team is expecting an ID.
     */
    override val keys: List<String> = listOf("id")

    /**
     * Executes the apartment update command using the specified identifier.
     *
     * @param args array of command arguments. A single String argument is expected, representing the identifier.
     */
    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()

        updateFlatStrategy.lifeTimeInsert(id)
    }

    /**
     * Executes an automatic apartment update command based on the specified arguments.
     *
     * @param args array of command arguments. Two arguments are expected: the identifier and the apartment data.
     */
    override fun autoExecute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()
        val data = args.elementAt(1)

        updateFlatStrategy.automaticallyInsert(id, flatDataConverter.convertStringToFlatData(data.toString()))
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