package org.example.kotlincollectionmanager.collection.items

import java.time.ZonedDateTime


class Flat(
    private var name: String? = null,
    private var coordinates: Coordinates? = Coordinates(),
    private var area: Long? = null,
    private var numberOfRooms: Long? = null,
    private var price: Long? = null,
    private var balcony: Boolean? = null,
    private var furnish: Furnish? = null,
    private var house: House? = null,
) {
    private var id: Long = 0
    private val creationDate: ZonedDateTime = ZonedDateTime.now()

    fun getId(): Long = id
    fun getName(): String? = name
    fun getCoordinates(): Coordinates? = coordinates
    fun getCreationDate(): ZonedDateTime = creationDate
    fun getArea(): Long? = area
    fun getNumberOfRooms(): Long? = numberOfRooms
    fun getPrice(): Long? = price
    fun getBalcony(): Boolean? = balcony
    fun getFurnish(): Furnish? = furnish
    fun getHouse(): House? = house

    fun setId(id: Long) = apply {
        if (id > 0) this.id = id else println("id must be greater than 0")
    }

    fun setName(name: String) = apply { this.name = name }

    fun setCoordinates(coordinates: Coordinates) = apply { this.coordinates = coordinates }

    fun setArea(area: Long) = apply {
        if (area in 1..996) this.area = area else println("Area should be between 1 and 996")
    }

    fun setNumberOfRooms(numberOfRooms: Long) = apply {
        if (numberOfRooms > 0) this.numberOfRooms = numberOfRooms else println("numberOfRooms must be greater than 0")
    }

    fun setPrice(price: Long) = apply {
        if (price in 1..682705217) this.price = price else println("price must be between 1 and 682705217")
    }

    fun setBalcony(balcony: Boolean) = apply { this.balcony = balcony }

    fun setFurnish(furnish: Furnish) = apply { this.furnish = furnish }

    fun setHouse(house: House) = apply { this.house = house }

    override fun toString(): String {
        return "Flat(id=$id, name='$name', coordinates=$coordinates, area=$area, numberOfRooms=$numberOfRooms, price=$price, balcony=$balcony, furnish=$furnish, house=$house, creationDate=$creationDate)"
    }


}