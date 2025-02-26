package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

@Component
class RemoveLowerKeyCommand(
    private val receiverService: ReceiverService,
    override val validator: OneArgCommandValidator
) : Command<OneArgCommandValidator> {
    override val name: String = "remove_lower_key"
    override val description: String = "Remove all flats with id lower than arguments id"
    override val keys: List<String>? = listOf("id")

    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLong()

        if (id == null) {
            println("Id format is invalid. Please try again")
        } else {
            receiverService.removeLowerKey(id)
        }
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}