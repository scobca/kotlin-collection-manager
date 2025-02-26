package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

@Component
class RemoveAllByBalconyCommand(
    private val receiverService: ReceiverService,
    override val validator: OneArgCommandValidator
) : Command<OneArgCommandValidator> {
    override val name: String = "remove_by_balcony"
    override val description: String = "Removes all flats by balcony parameter"
    override val keys: List<String>? = listOf("true | false")

    override fun execute(vararg args: String?) {
        val bool = args.elementAt(0)

        if (bool.equals("true", ignoreCase = true) || bool.equals("false", ignoreCase = true)) {
            if (bool != null) {
                receiverService.removeAllByBalcony(bool)
            }
        } else {
            println("Unknown command arguments. Please try again later")
        }
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}