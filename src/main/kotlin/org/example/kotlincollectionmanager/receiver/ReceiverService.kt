package org.example.kotlincollectionmanager.receiver

import org.example.kotlincollectionmanager.collection.Collection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReceiverService(@Autowired private var collection: Collection) {
    fun showCollection() {
        collection.flats
    }
}