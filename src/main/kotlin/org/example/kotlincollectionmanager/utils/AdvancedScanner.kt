package org.example.kotlincollectionmanager.utils

import org.springframework.stereotype.Component
import java.util.*

/**
 * A custom Scanner class for reading and processing user input from the console.
 * Provides methods for cycling the input request until the correct value is received.
 */
@Component
class AdvancedScanner {
    /**
     * A scanner for reading input from the console.
     */
    val scanner = Scanner(System.`in`)

    /**
     * Cyclically requests user input until the correct value is received.
     *
     * @param message is a message to output to the user.
     * @param converter is a function for converting a string to type T.
     * @return Converted value of type T.
     */
    fun <T> cycleScan(message: String, converter: (String) -> T): T {
        var value: T? = null
        while (value == null) {
            try {
                print(message)

                val newValue = scanner.nextLine().trim()
                value = converter(newValue)
            } catch (e: NumberFormatException) {
                println("Invalid input. Please try again.")
            } catch (e: Exception) {
                println("An error occurred: ${e.message}")
            }
        }

        return value
    }

    /**
     * Similar to cycleScan, but allows the user to leave the current value of a variable by entering an empty string.
     *
     * @param message is a message to output to the user.
     * @param oldValue is the current value that will be returned if the user doesn't enter anything.
     * @param converter is a function for converting a string to type T.
     * @return is either the converted new value or the old value.
     */
    fun <T> cycleUpdateScan(message: String, oldValue: T, converter: (String) -> T): T {
        var value: T? = null
        while (value == null) {
            try {
                print(message)

                val newValue = scanner.nextLine().trim()
                value = if (newValue == "") {
                    oldValue
                } else {
                    converter(newValue)
                }
            } catch (e: NumberFormatException) {
                println("Invalid input. Please try again.")
            } catch (e: Exception) {
                println("An error occurred: ${e.message}")
            }
        }

        return value
    }

    /**
     * A specialized method for reading and converting a boolean value from CLI.
     * Prompts for input until the user enters either "true" or "false".
     *
     * @param message is a message to output to the user.
     * @return is a boolean value entered by the user.
     */
    fun cycleScanBoolean(message: String): Boolean {
        var value: Boolean? = null
        while (value == null) {
            try {
                print(message)

                val input = scanner.nextLine().trim().lowercase()
                when (input) {
                    "true" -> value = true
                    "false" -> value = false
                    else -> println("Invalid input. Please enter 'true' or 'false'.")
                }
            } catch (e: Exception) {
                println("An error occurred: ${e.message}")
            }
        }
        return value
    }
}
