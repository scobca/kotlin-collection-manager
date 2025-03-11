package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * Command for calculating and outputting the average price of all apartments in the collection.
 * Implements the Command interface and uses the NoArgsCommand Validator to check for missing arguments.
 */
@Component
class AveragePriceCommand(
    /**
     * Validator for checking the absence of arguments.
     */
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    /**
     * A service for interacting with a collection of apartments.
     */
    private val receiverService: ReceiverService
) : Command<NoArgsCommandValidator> {
    /**
     * The name of the command.
     */
    override val name: String = "average_price"

    /**
     * Description of the command.
     */
    override val description: String = "Return average prices of all flats in collection"

    /**
     * The list of keywords for the command is null, because the command does not require arguments.
     */
    override val keys: List<String>? = null

    /**
     * Executes the command by calling the getAveragePrice method of the Receivservice service.
     *
     * @param args Command arguments (not used).
     */
    override fun execute(vararg args: String?) {
        receiverService.getAveragePrice()
    }

    /**
     * Checks the command arguments using the NoArgsCommandValidator.
     *
     * @param args List of command arguments.
     */
    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}