package org.example.kotlincollectionmanager.command.validators

import org.example.kotlincollectionmanager.command.intefaces.Validator
import org.springframework.stereotype.Component

@Component
class NoArgsCommandValidator : Validator {
    override fun validate(args: List<String>) : String {
        return if (args.isEmpty()) {
            "OK"
        } else {
            "Bad Args"
        }
    }
}