package org.example.kotlincollectionmanager

import org.example.kotlincollectionmanager.invoker.InvokerService
import org.example.kotlincollectionmanager.parser.JsonParser
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import java.io.FileInputStream
import java.io.IOException

@SpringBootApplication
class KotlinCollectionManagerApplication(private val invokerService: InvokerService, private val parser: JsonParser) :
    CommandLineRunner {

    override fun run(vararg args: String?) {
        if (args.isNotEmpty()) {
            val fileName = args[0]?.trim()
            val resource = if (fileName?.contains(".json") == true) {
                File("$fileName")
            } else {
                File("$fileName.json")
            }

            if (resource.exists()) {
                if (resource.length() > 0) {
                    val inputStream = FileInputStream(resource)
                    parser.loadFlats(inputStream)
                }
            } else {
                val file = if (fileName?.contains(".json") == true) {
                    File("$fileName")
                } else {
                    File("$fileName.json")
                }

                try {
                    if (file.createNewFile()) {
                        println("Файл $fileName создан успешно.")
                    } else {
                        println("Ошибка при создании файла $fileName")
                    }
                } catch (e: IOException) {
                    println("Ошибка при создании файла: $e")
                }
            }
        }

        invokerService.run()
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinCollectionManagerApplication>(*args)
}
