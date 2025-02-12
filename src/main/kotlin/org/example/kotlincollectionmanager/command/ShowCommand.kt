package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class ShowCommand(
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    private val receiverService: ReceiverService
) : Command<NoArgsCommandValidator> {
    override val name: String = "show"
    override val description: String = "Outputs all the elements of the collection in a string representation"
    override val keys: List<String>? = null

    override fun execute(vararg args: String?) {
        receiverService.show()
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}