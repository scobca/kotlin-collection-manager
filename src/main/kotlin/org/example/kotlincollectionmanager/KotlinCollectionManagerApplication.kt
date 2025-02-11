package org.example.kotlincollectionmanager

import org.example.kotlincollectionmanager.invoker.InvokerService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinCollectionManagerApplication(private val invokerService: InvokerService) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val filename: String? = System.getProperty("file")
        invokerService.run()
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinCollectionManagerApplication>(*args)
}
