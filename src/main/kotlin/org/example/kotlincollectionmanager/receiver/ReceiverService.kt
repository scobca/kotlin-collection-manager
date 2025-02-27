package org.example.kotlincollectionmanager.receiver

import org.example.kotlincollectionmanager.collection.Collection
import org.example.kotlincollectionmanager.collection.items.Flat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ReceiverService(@Autowired private var collection: Collection) {
    fun getElementById(id: Long): Flat? {
        return collection[id]
    }

    fun getFlats(): TreeMap<Long, Flat> {
        return collection.getFlats()
    }

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
                println("   ——> ${flat.value}")
            }
        } else println("No flats available in collection")
    }

    fun insert(flat: Flat) {
        collection.getFlats()[flat.getId()] = flat
        println("Flat created successfully")
    }

    fun update(flat: Flat) {
        collection.getFlats()[flat.getId()] = flat
        println("Flat updated successfully")
    }

    fun remove(flatId: Long) {
        collection.getFlats().remove(flatId)
        println("Flat with id: $flatId removed successfully")
    }

    fun clear() {
        collection.getFlats().clear()
    }

    fun replaceIfLower(id: Long, flat: Flat) {
        val comparableFlat = collection.getFlats()[id]

        if (comparableFlat != null && comparableFlat < flat) {
            collection[id] = flat
            println("Flat replaced successfully")
        } else println("Flat replaced failed")
    }

    fun removeLowerKey(id: Long) {
        val flats = collection.getFlats()
        val removableFlats = mutableListOf<Long>()

        flats.forEach { flat ->
            if (flat.key < id) {
                removableFlats.add(flat.key)
            }
        }

        removableFlats.forEach { flatId -> remove(flatId) }
    }

    fun removeAllByBalcony(bool: String) {
        val flats = collection.getFlats()

        flats.forEach { flat ->
            if (flat.value.getBalcony().toString() == bool) {
                remove(flat.key)
            }
        }
    }

    fun getAveragePrice() {
        var allPrices: Long = 0
        val flatsCount = collection.getFlats().size

        if (flatsCount > 0) {
            collection.getFlats().forEach { flat ->
                if (flat.value.getPrice() != null) {
                    allPrices += flat.value.getPrice()!!
                }
            }
            println("Average price for flats: ${allPrices / flatsCount}")
        } else {
            println("No flats available. Cannot get the average price")
        }
    }

    fun filterContainsName(name: String) {
        val flats = collection.getFlats()

        flats.forEach { flat ->
            if (flat.value.getName() != null) {
                if (flat.value.getName()!!.contains(name, ignoreCase = true)) println(flat.value)
            }
        }
    }
}