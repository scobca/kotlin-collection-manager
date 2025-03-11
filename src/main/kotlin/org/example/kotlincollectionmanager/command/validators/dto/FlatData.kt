package org.example.kotlincollectionmanager.command.validators.dto

import org.example.kotlincollectionmanager.collection.items.Furnish

/**
 * A class for representing flat data.
 * Contains properties for the name, coordinates, area, number of rooms, price, balcony, finishing level, and other characteristics.
 */
data class FlatData(
    /**
     * The name of the apartment.
     */
    val name: String,

    /**
     * X coordinate.
     */
    val cordX: Long,

    /**
     * Y coordinate.
     */
    val cordY: Float,

    /**
     * The area of the apartment.
     */
    val area: Long,

    /**
     * The number of rooms in the apartment.
     */
    val numberOfRooms: Long,

    /**
     * The price of the apartment.
     */
    val price: Long,

    /**
     * The presence of a balcony.
     */
    val balcony: Boolean,

    /**
     * Apartment finishing level (Furniture type).
     */
    val furnish: Furnish,

    /**
     * The name of the house where the apartment is located.
     */
    val houseName: String,

    /**
     * The year the house was built.
     */
    val year: Int,

    /**
     * The number of floors in the house.
     */
    val numberOfFloors: Long,
)