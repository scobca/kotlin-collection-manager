package org.example.kotlincollectionmanager.command.validators.config

import org.example.kotlincollectionmanager.command.validators.HelpCommandValidator
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.command.validators.OneOrTwoArgsValidator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ValidatorConfig {
    @Bean
    fun noArgsCommandValidator(): NoArgsCommandValidator {
        return NoArgsCommandValidator()
    }

    @Bean
    fun oneArgCommandValidator(): OneArgCommandValidator {
        return OneArgCommandValidator()
    }

    @Bean
    fun oneOrTwoArgsValidator(): OneOrTwoArgsValidator {
        return OneOrTwoArgsValidator()
    }

    @Bean
    fun helpCommandValidator(): HelpCommandValidator {
        return HelpCommandValidator()
    }
}