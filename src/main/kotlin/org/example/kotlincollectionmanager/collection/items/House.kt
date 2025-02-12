package org.example.kotlincollectionmanager.collection.items

class House(
    private var name: String,
    private var year: Int,
    private var numberOfFloors: Long
) {
    fun getName(): String = name
    fun getYear(): Int = year
    fun getNumberOfFloors(): Long = numberOfFloors

    override fun toString(): String {
        return "House(name='$name', year=$year, numberOfFloors=$numberOfFloors)"
    }
}