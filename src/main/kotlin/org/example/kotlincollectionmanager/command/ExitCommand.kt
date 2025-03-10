package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.interfaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class ExitCommand(
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    @Lazy private val invokerService: InvokerService
) :
    Command<NoArgsCommandValidator> {
    override val name: String = "exit"
    override val description: String = "Program termination (without saving to a file)"
    override val keys: List<String>? = null

    override fun execute(vararg args: String?) {
        println("Exiting...")
        invokerService.closeScanner()
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }

}