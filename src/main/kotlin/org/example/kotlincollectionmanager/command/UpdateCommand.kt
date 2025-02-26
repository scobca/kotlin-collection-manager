package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.AutoCommand
import org.example.kotlincollectionmanager.command.validators.OneOrTwoArgsValidator
import org.example.kotlincollectionmanager.strategies.UpdateFlatStrategy
import org.example.kotlincollectionmanager.utils.StringToFlatDataConverter
import org.springframework.stereotype.Component

@Component
class UpdateCommand(
    override val validator: OneOrTwoArgsValidator,
    private val updateFlatStrategy: UpdateFlatStrategy,
    private val flatDataConverter: StringToFlatDataConverter,
) :
    AutoCommand<OneOrTwoArgsValidator> {
    override val name: String = "update"
    override val description: String = "Updates the command by it's Id"
    override val keys: List<String> = listOf("id")

    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()

        updateFlatStrategy.lifeTimeInsert(id)
    }

    override fun autoExecute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()
        val data = args.elementAt(1)

        updateFlatStrategy.automaticallyInsert(id, flatDataConverter.convertStringToFlatData(data.toString()))
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}