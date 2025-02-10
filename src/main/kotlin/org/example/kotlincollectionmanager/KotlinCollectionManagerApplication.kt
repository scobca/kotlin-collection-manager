package org.example.kotlincollectionmanager

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinCollectionManagerApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Kotlin Collection Manager started")
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinCollectionManagerApplication>(*args)
}
