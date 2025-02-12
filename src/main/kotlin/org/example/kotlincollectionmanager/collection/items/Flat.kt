package org.example.kotlincollectionmanager.collection.items

import java.time.ZonedDateTime


class Flat(
    private var name: String,
    private var coordinates: Coordinates,
    private var area: Long,
    private var numberOfRooms: Long,
    private var price: Long,
    private var balcony: Boolean,
    private var furnish: Furnish,
    private var house: House,
) {
    private var id: Long = 1
    private val creationDate: ZonedDateTime = ZonedDateTime.now()

    init {
        require(id > 0) { "id must be greater than 0" }
        require(area in 1..996) { "area must be between 1 and 996" }
        require(numberOfRooms > 0) { "numberOfRooms must be greater than 0" }
        require(price in 1..682705217) { "price must be between 1 and 682705217" }
    }

    fun getId(): Long = id
    fun getName(): String = name
    fun getCoordinates(): Coordinates = coordinates
    fun getCreationDate(): ZonedDateTime = creationDate
    fun getArea(): Long = area
    fun getNumberOfRooms(): Long = numberOfRooms
    fun getPrice(): Long = price
    fun getBalcony(): Boolean = balcony
    fun getFurnish(): Furnish = furnish
    fun getHouse(): House = house

    fun setId(id: Long) = apply {this.id = id}
    fun setName(name: String) = apply {this.name = name}
    fun setCoordinates(coordinates: Coordinates) = apply {this.coordinates = coordinates}
    fun setArea(area: Long) = apply {this.area = area}
    fun setNumberOfRooms(numberOfRooms: Long) = apply {this.numberOfRooms = numberOfRooms}
    fun setPrice(price: Long) = apply {this.price = price}
    fun setBalcony(balcony: Boolean) = apply {this.balcony = balcony}
    fun setFurnish(furnish: Furnish) = apply {this.furnish = furnish}
    fun setHouse(house: House) = apply {this.house = house}

    override fun toString(): String {
        return "Flat(id=$id, name='$name', coordinates=$coordinates, area=$area, numberOfRooms=$numberOfRooms, price=$price, balcony=$balcony, furnish=$furnish, house=$house, creationDate=$creationDate)"
    }


}