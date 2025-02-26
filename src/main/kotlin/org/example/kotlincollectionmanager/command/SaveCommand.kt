package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.parser.JsonParser
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

@Component
class SaveCommand(
    override val validator: OneArgCommandValidator,
    private val parser: JsonParser,
    private val receiverService: ReceiverService
) : Command<OneArgCommandValidator> {
    override val name: String = "save"
    override val description: String = "Saves flats to JSON file"
    override val keys: List<String>? = listOf("filename")

    override fun execute(vararg args: String?) {
        val filename = args.elementAt(0)

        if (filename != null) {
            val flats = receiverService.getFlats()

            parser.saveFlats(flats, filename)
        }
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}