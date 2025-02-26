package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component

@Component
class FilterContainsNameCommand(override val validator: OneArgCommandValidator, private val receiverService: ReceiverService) : Command<OneArgCommandValidator> {
    override val name: String = "filter_contains_name"
    override val description: String = "Return all flats, which name contains similar word from key"
    override val keys: List<String>? = listOf("name")

    override fun execute(vararg args: String?) {
        val sub = args.elementAt(0)

        if (sub == null) {
            println("Incorrect input. Please try again")
        } else {
            receiverService.filterContainsName(sub)
        }
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}