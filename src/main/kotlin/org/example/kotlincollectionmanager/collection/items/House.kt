package org.example.kotlincollectionmanager.collection.items

class House(
    private var name: String? = null,
    private var year: Int? = null,
    private var numberOfFloors: Long? = null,
) {
    fun getName(): String? = name
    fun getYear(): Int? = year
    fun getNumberOfFloors(): Long? = numberOfFloors

    fun setName(name: String) = apply { this.name = name }
    fun setYear(year: Int) = apply { this.year = year }
    fun setNumberOfFloors(numberOfFloors: Long) = apply { this.numberOfFloors = numberOfFloors }

    override fun toString(): String {
        return "House(name='$name', year=$year, numberOfFloors=$numberOfFloors)"
    }
}