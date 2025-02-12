package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class ClearCommand(
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    private val receiverService: ReceiverService
) : Command<NoArgsCommandValidator> {
    override val name: String = "clear"
    override val description: String = "Clears the flats collection"
    override val keys: List<String>? = null

    override fun execute(vararg args: String?) {
        receiverService.clear()
        println("Collection cleared successfully")
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }

}