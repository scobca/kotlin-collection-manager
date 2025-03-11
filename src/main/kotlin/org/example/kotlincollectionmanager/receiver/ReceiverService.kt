package org.example.kotlincollectionmanager.receiver

import org.example.kotlincollectionmanager.collection.Collection
import org.example.kotlincollectionmanager.collection.items.Flat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

/**
 * A service for interacting with a collection of objects.
 * Provides methods for adding, deleting, updating, and displaying collection items.
 */
@Component
class ReceiverService(
    /**
     * Collection of objects.
     */
    @Autowired private var collection: Collection
) {
    /**
     * Returns a collection item by its ID.
     *
     * @param id is the element ID.
     * @return the collection item, or null if the item is not found.
     */
    fun getElementById(id: Long): Flat? {
        return collection[id]
    }

    /**
     * Returns all the elements of the collection as a sorted map.
     *
     * @return map of collection items.
     */
    fun getFlats(): TreeMap<Long, Flat> {
        return collection.getFlats()
    }

    /**
     * Displays information about the collection.
     */
    fun info() {
        println("Collection type: ${collection.getFlats()::class.simpleName}")
        println("Initialization date: ${collection.getInitDate()}")
        show()
        println("Elements in collection: ${collection.getFlats().size}")
    }

    /**
     * Outputs all the elements of the collection.
     */
    fun show() {
        if (collection.getFlats().size > 0) {
            println("Available flats:")
            collection.getFlats().forEach { flat ->
                println("   ——> ${flat.value}")
            }
        } else println("No flats available in collection")
    }

    /**
     * Adds a new item to the collection.
     *
     * @param flat element to add.
     */
    fun insert(flat: Flat) {
        collection.getFlats()[flat.getId()] = flat
        println("Flat created successfully")
    }

    /**
     * Updates an existing item in the collection.
     *
     * @param flat updated element.
     */
    fun update(flat: Flat) {
        collection.getFlats()[flat.getId()] = flat
        println("Flat updated successfully")
    }

    /**
     * Deletes an item from the collection by its ID.
     *
     * @param flatId is the ID of the item to delete.
     */
    fun remove(flatId: Long) {
        collection.getFlats().remove(flatId)
        println("Flat with id: $flatId removed successfully")
    }

    /**
     * Clears the collection.
     */
    fun clear() {
        collection.getFlats().clear()
    }

    /**
     * Replaces an item in the collection with a new one if the new item is better.
     *
     * @param id is the ID of the replacement item.
     * @param flat new element.
     */
    fun replaceIfLower(id: Long, flat: Flat) {
        val comparableFlat = collection.getFlats()[id]

        if (comparableFlat != null && comparableFlat < flat) {
            collection[id] = flat
            println("Flat replaced successfully")
        } else println("Flat replaced failed")
    }

    /**
     * Deletes all items with an ID lower than the specified one.
     *
     * @param id is the identifier for comparison.
     */
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

    /**
     * Deletes all elements that have a balcony corresponding to the specified value.
     *
     * @param bool string representation of the presence of a balcony ("true" or "false").
     */
    fun removeAllByBalcony(bool: String) {
        val flats = collection.getFlats()

        flats.forEach { flat ->
            if (flat.value.getBalcony().toString() == bool) {
                remove(flat.key)
            }
        }
    }

    /**
     * Outputs the average price of all items in the collection.
     */
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

    /**
     * Outputs all the elements whose name contains the specified string.
     *
     * @param name is a string for searching in the names of elements.
     */
    fun filterContainsName(name: String) {
        val flats = collection.getFlats()

        flats.forEach { flat ->
            if (flat.value.getName() != null) {
                if (flat.value.getName()!!.contains(name, ignoreCase = true)) println(flat.value)
            }
        }
    }
}