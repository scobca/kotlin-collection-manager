package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.AutoCommand
import org.example.kotlincollectionmanager.command.validators.OneOrTwoArgsValidator
import org.example.kotlincollectionmanager.strategies.InsertFlatStrategy
import org.example.kotlincollectionmanager.utils.StringFlatDataConverter
import org.springframework.stereotype.Component

@Component
class InsertCommand(
    override val validator: OneOrTwoArgsValidator,
    private val insertFlatStrategy: InsertFlatStrategy,
    private val flatDataConverter: StringFlatDataConverter
) : AutoCommand<OneOrTwoArgsValidator> {
    override val name: String = "insert"
    override val description: String = "Adds a new element with the specified key"
    override val keys: List<String>? = listOf("id")

    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()

        insertFlatStrategy.lifeTimeInsert(id)
    }

    override fun autoExecute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()
        val data = args.elementAt(1)

        insertFlatStrategy.automaticallyInsert(id, flatDataConverter.convertStringToFlatData(data.toString()))
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}