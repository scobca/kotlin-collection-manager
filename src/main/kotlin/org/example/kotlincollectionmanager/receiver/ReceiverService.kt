package org.example.kotlincollectionmanager.receiver

import org.example.kotlincollectionmanager.collection.Collection
import org.example.kotlincollectionmanager.collection.items.Flat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReceiverService(@Autowired private var collection: Collection) {
    fun info() {
        println("Collection type: ${collection.getFlats()::class.simpleName}")
        println("Initialization date: ${collection.getInitDate()}")
        show()
        println("Elements in collection: ${collection.getFlats().size}")
    }

    fun show() {
        if (collection.getFlats().size > 0) {
            println("Available flats:")
            collection.getFlats().forEach { flat ->
                println(flat.value.toString())
            }
        } else println("No flats available in collection")
    }

    fun insert(flat: Flat) {
        if (collection.getFlats().isNotEmpty()) {
            val lastIndex = collection.getFlats().lastKey()
            flat.setId(lastIndex + 1)
            collection.getFlats()[flat.getId()] = flat
        } else {
            flat.setId(1)
            collection.getFlats()[flat.getId()] = flat
        }
    }

    fun clear() {
        collection.getFlats().clear()
    }
}