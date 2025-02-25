package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.invoker.InvokerService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class History(
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    @Lazy private val invokerService: InvokerService
) : Command<NoArgsCommandValidator> {
    override val name: String = "history"
    override val description: String = "Return last 10 commands from cli"
    override val keys: List<String>? = null

    override fun execute(vararg args: String?) {
        println("Recent commands list:")
        invokerService.getCommandsHistory().forEach { command -> println("——> $command") }
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}