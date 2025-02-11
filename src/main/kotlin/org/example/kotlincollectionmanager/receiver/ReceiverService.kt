package org.example.kotlincollectionmanager.receiver

import org.example.kotlincollectionmanager.collection.Flat
import org.springframework.stereotype.Component
import java.util.*

@Component
class ReceiverService {
    val collection = TreeMap<Long, Flat>()

    fun help() {
        println("help")
    }
}