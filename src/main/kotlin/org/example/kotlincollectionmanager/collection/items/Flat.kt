package org.example.kotlincollectionmanager.collection.items

import java.time.ZonedDateTime

/**
 * A class for presenting information about an flat.
 * It contains fields for the identifier, name, coordinates, area, number of rooms, price, balcony, decoration level, and house.
 */
class Flat(
    /**
     * The name of the flat. It can be null.
     */
    private var name: String? = null,

    /**
     * The coordinates of the flat. It can be null.
     */
    private var coordinates: Coordinates? = Coordinates(),

    /**
     * The area of the flat. It can be null.
     */
    private var area: Long? = null,

    /**
     * The number of rooms in the flat. It can be null.
     */
    private var numberOfRooms: Long? = null,

    /**
     * The price of the flat. It can be null.
     */
    private var price: Long? = null,

    /**
     * The presence of a balcony. It can be null.
     */
    private var balcony: Boolean? = null,

    /**
     * The flat's finishing level. It can be null.
     */
    private var furnish: Furnish? = null,

    /**
     * The house where the flat is located. It can be null.
     */
    private var house: House? = null,
) : Comparable<Flat> {
    /**
     * The unique identifier of the flat.
     */
    private var id: Long = 0

    /**
     * The date when the flat object was created.
     */
    private val creationDate: ZonedDateTime = ZonedDateTime.now()

    /**
     * Returns the flat ID.
     *
     *  @return flat ID.
     */
    fun getId(): Long = id

    /**
     * Returns the name of the flat.
     *
     * @return flat name or null.
     */
    fun getName(): String? = name

    /**
     * Returns the coordinates of the flat.
     *
     * @return flat coordinates or null.
     */
    fun getCoordinates(): Coordinates? = coordinates

    /**
     * Returns the date when the flat object was created.
     *
     * @return Date of the flat object creation.
     */
    fun getCreationDate(): ZonedDateTime = creationDate

    /**
     * Returns the area of the flat.
     *
     * @return The area of the flat or null.
     */
    fun getArea(): Long? = area

    /**
     * Returns the number of rooms in the flat.
     *
     * @return The number of rooms or null.
     */
    fun getNumberOfRooms(): Long? = numberOfRooms

    /**
     * Returns the price of the flat.
     *
     * @return flat price or null.
     */
    fun getPrice(): Long? = price

    /**
     * Returns information about the presence of a balcony.
     *
     * @return The presence of a balcony or null.
     */
    fun getBalcony(): Boolean? = balcony

    /**
     * Returns the flat's finishing level.
     *
     * @return Furnish or null.
     */
    fun getFurnish(): Furnish? = furnish

    /**
     * Returns the house where the flat is located.
     *
     * @return Home or null.
     */
    fun getHouse(): House? = house

    /**
     * Sets the flat ID if it is greater than 0.
     *
     * @param id is a new identifier.
     * @return The current Flat object.
     */
    fun setId(id: Long) = apply {
        if (id > 0) this.id = id else println("id must be greater than 0")
    }

    /**
     * Sets the name of the flat if it is not empty.
     *
     * @param name New name.
     * @return The current Flat object.
     */
    fun setName(name: String) = apply { if (name != "") this.name = name else println("Name cannot be empty") }

    /**
     * Sets the coordinates of the flat.
     *
     * @param coordinates New coordinates.
     * @return The current Flat object.
     */
    fun setCoordinates(coordinates: Coordinates) = apply { this.coordinates = coordinates }

    /**
     * Sets the area of the flat if it is in the range from 1 to 996.
     *
     * @param area is a new square.
     * @return The current Flat object.
     */
    fun setArea(area: Long) = apply {
        if (area in 1..996) this.area = area else println("Area should be between 1 and 996")
    }

    /**
     * Sets the number of rooms in the flat if it is more than 0.
     *
     * @param numberOfRooms New number of rooms.
     * @return The current Flat object.
     */
    fun setNumberOfRooms(numberOfRooms: Long) = apply {
        if (numberOfRooms > 0) this.numberOfRooms = numberOfRooms else println("numberOfRooms must be greater than 0")
    }

    /**
     * Sets the price of the flat if it is in the range from 1 to 682705217.
     *
     * @param price New price.
     * @return The current Flat object.
     */
    fun setPrice(price: Long) = apply {
        if (price in 1..682705217) this.price = price else println("price must be between 1 and 682705217")
    }

    /**
     * Sets information about the presence of a balcony.
     *
     * @param balcony The presence of a balcony.
     * @return The current Flat object.
     */
    fun setBalcony(balcony: Boolean) = apply { this.balcony = balcony }

    /**
     * Sets the finishing level of the flat.
     *
     * @param furnish A new level of finish.
     * @return The current Flat object.
     */
    fun setFurnish(furnish: Furnish) = apply { this.furnish = furnish }

    /**
     * Sets the house where the flat is located.
     *
     * @param house is a new house.
     * @return The current Flat object.
     */
    fun setHouse(house: House) = apply { this.house = house }

    /**
     * Returns a string representation of the flat object.
     *
     * @return String representation of the object.
     */
    override fun toString(): String {
        return "Flat(id=$id, name='$name', coordinates=$coordinates, area=$area, numberOfRooms=$numberOfRooms, price=$price, balcony=$balcony, furnish=$furnish, house=$house, creationDate=$creationDate)"
    }

    /**
     * Compares two flats by price and area.
     *
     * @param other Another flat for comparison.
     * @return The result of the comparison.
     */
    override fun compareTo(other: Flat): Int {
        val price = this.price ?: 0L
        val otherPrice = other.price ?: 0L

        val area = this.area ?: 0L
        val otherArea = other.area ?: 0L

        return (price.compareTo(otherPrice) + area.compareTo(otherArea))
    }
}