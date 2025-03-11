package org.example.kotlincollectionmanager.command.validators.config

import org.example.kotlincollectionmanager.command.validators.HelpCommandValidator
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.command.validators.OneOrTwoArgsValidator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration class for creating and registering command validators in the application context.
 */
@Configuration
class ValidatorConfig {

    /**
     * Creates and registers a validator bin for commands without arguments.
     *
     * @return Instance of NoArgsCommandValidator.
     */
    @Bean
    fun noArgsCommandValidator(): NoArgsCommandValidator {
        return NoArgsCommandValidator()
    }

    /**
     * Creates and registers a validator bin for commands with one argument.
     *
     * @return Instance of OneArgCommandValidator.
     */
    @Bean
    fun oneArgCommandValidator(): OneArgCommandValidator {
        return OneArgCommandValidator()
    }


    /**
     * Creates and registers a validator bin for commands with one or two arguments.
     *
     * @return Instance of OneOrTwoArgsValidator.
     */
    @Bean
    fun oneOrTwoArgsValidator(): OneOrTwoArgsValidator {
        return OneOrTwoArgsValidator()
    }

    /**
     * Creates and registers a validator bin for the help command.
     *
     * @return An instance of HelpCommandValidator.
     */
    @Bean
    fun helpCommandValidator(): HelpCommandValidator {
        return HelpCommandValidator()
    }
}