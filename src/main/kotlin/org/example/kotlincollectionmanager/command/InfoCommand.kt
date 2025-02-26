package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class InfoCommand(
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    private val receiverService: ReceiverService
) : Command<NoArgsCommandValidator> {
    override val name: String = "info"
    override val description: String = "Display information about the collection"
    override val keys: List<String>? = null

    override fun execute(vararg args: String?) {
        receiverService.info()
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}