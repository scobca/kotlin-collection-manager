package org.example.kotlincollectionmanager.collection

import org.example.kotlincollectionmanager.collection.items.Flat
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

/**
 * A component for storing and managing a collection of Flat objects.
 * Provides functionality for adding, receiving, and deleting objects by their IDs.
 */
@Component
class Collection {
    /**
     * Date and time of collection initialization.
     */
    private val initDate: ZonedDateTime = ZonedDateTime.now()

    /**
     * A sorted map of Flat objects, where the key is the apartment identifier.
     */
    private var flats = TreeMap<Long, Flat>()

    /**
     * Returns the date and time of collection initialization.
     *
     * @return Initialization date and time.
     */
    fun getInitDate(): ZonedDateTime = initDate

    /**
     * Returns the entire collection of Flat objects.
     *
     * @return is a collection of Flat objects.
     */
    fun getFlats(): TreeMap<Long, Flat> = flats

    /**
     * Returns the Flat object by its ID.
     * If there is no object with this ID, returns null.
     *
     * @param id is the apartment ID.
     * @return A Flat or null object.
     */
    operator fun get(id: Long): Flat? {
        return flats[id]
    }

    /**
     * Adds or updates a Flat object to the collection by its ID.
     *
     * @param id is the apartment ID.
     * @param value Flat object to add or update.
     */
    operator fun set(id: Long, value: Flat) {
        flats[id] = value
    }
}