package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.AutoCommand
import org.example.kotlincollectionmanager.command.validators.OneOrTwoArgsValidator
import org.example.kotlincollectionmanager.strategies.InsertFlatStrategy
import org.example.kotlincollectionmanager.utils.StringToFlatDataConverter
import org.springframework.stereotype.Component

/**
 * Command to add a new item to the collection.
 * Implements the AutoCommand interface and uses OneOrTwoArgsValidator to validate arguments.
 */
@Component
class InsertCommand(
    /**
     * Validator for checking the number of arguments (1 or 2).
     * Ensures that the command is called correctly, taking into account the operating mode.
     */
    override val validator: OneOrTwoArgsValidator,
    /**
     * A strategy for adding items to a collection.
    Defines the logic of interaction with the collection when adding new items.
     */
    private val insertFlatStrategy: InsertFlatStrategy,
    /**
     * Converter of a data string to a FlatData object.
     * Used to convert a JSON string into a data object during automatic input.
     */
    private val flatDataConverter: StringToFlatDataConverter
) : AutoCommand<OneOrTwoArgsValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "insert"

    /**
     * Description of the command.
     */
    override val description: String = "Adds a new element with the specified key"

    /**
     * The keyword list for the command is ["id"], indicating that the command is expecting an identifier.
     */
    override val keys: List<String>? = listOf("id")

    /**
     * Executes the command, adding a new item to the collection.
     *
     * @param args Command arguments are the element ID.
     */
    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()

        insertFlatStrategy.lifeTimeExecution(id)
    }

    /**
     * Executes the command automatically, adding a new item to the collection.
     *
     * @param args Command arguments are the element identifier and the data string.
     */
    override fun autoExecute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()
        val data = args.elementAt(1)

        insertFlatStrategy.automaticallyExecution(id, flatDataConverter.convertStringToFlatData(data.toString()))
    }

    /**
     * Checks the arguments of the command using OneOrTwoArgsValidator.
     *
     * @param args List of command arguments.
     */
    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}