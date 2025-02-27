package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.AutoCommand
import org.example.kotlincollectionmanager.command.validators.OneOrTwoArgsValidator
import org.example.kotlincollectionmanager.strategies.ReplaceIfLowerStrategy
import org.example.kotlincollectionmanager.utils.StringToFlatDataConverter
import org.springframework.stereotype.Component

@Component
class ReplaceIfLowerCommand(
    override val validator: OneOrTwoArgsValidator,
    private val replaceIfLowerStrategy: ReplaceIfLowerStrategy,
    private val flatDataConverter: StringToFlatDataConverter
) : AutoCommand<OneOrTwoArgsValidator> {
    override val name: String = "replace_if_lower"
    override val description: String = "Replace a flat from a collection if it lower than new flat"
    override val keys: List<String> = listOf("id")

    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLong()

        replaceIfLowerStrategy.lifeTimeInsert(id)
    }

    override fun autoExecute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()
        val data = args.elementAt(1)

        replaceIfLowerStrategy.automaticallyInsert(id, flatDataConverter.convertStringToFlatData(data.toString()))
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}