package org.example.kotlincollectionmanager.collection.items

/**
 * A class for presenting information about a house.
 * Contains fields for the name of the house, the year of construction, and the number of floors.
 */
class House(
    /**
     * The name of the house. It can be null.
     */
    private var name: String? = null,
    /**
     * The year the house was built. It can be null.
     */
    private var year: Int? = null,
    /**
     * The number of floors in the house. It can be null.
     */
    private var numberOfFloors: Long? = null,
) {
    /**
     * Returns the name of the house.
     *
     * @return The name of the house or null.
     */
    fun getName(): String? = name

    /**
     * Returns the year the house was built.
     *
     * @return Year of construction or null.
     */
    fun getYear(): Int? = year

    /**
     * Returns the number of floors in the house.
     *
     * @return The number of floors or null.
     */
    fun getNumberOfFloors(): Long? = numberOfFloors

    /**
     * Sets the name of the house and returns the current object for chained method invocation.
     *
     * @param name is the new name of the house.
     * @return The current House object.
     */
    fun setName(name: String) = apply { this.name = name }

    /**
     * Sets the year the house was built and returns the current object for chained method invocation.
     *
     * @param year is the new year of construction.
     * @return The current House object.
     */
    fun setYear(year: Int) = apply { this.year = year }

    /**
     * Sets the number of floors in the house and returns the current object for chained method calls.
     *
     * @param numberOfFloors New number of floors.
     * @return The current House object.
     */
    fun setNumberOfFloors(numberOfFloors: Long) = apply { this.numberOfFloors = numberOfFloors }

    /**
     * Returns a string representation of the object in the format "House(name='name', year=year, numberOfFloors=number Of Floors)".
     *
     * @return String representation of the object.
     */
    override fun toString(): String {
        return "House(name='$name', year=$year, numberOfFloors=$numberOfFloors)"
    }
}