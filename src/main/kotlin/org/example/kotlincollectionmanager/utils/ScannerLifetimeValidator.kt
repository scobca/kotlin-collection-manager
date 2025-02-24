package org.example.kotlincollectionmanager.utils

import java.util.*

object ScannerLifetimeValidator {
    fun catchAndBreak(message: String, scanner: Scanner) {
        println(message)
        scanner.close()
    }
}