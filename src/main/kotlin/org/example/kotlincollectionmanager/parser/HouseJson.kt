package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A class for representing a house in JSON format.
 * Used to deserialize house data from a JSON string.
 */
data class HouseJson(
    /**
     * The name of the house.
     */
    @JsonProperty("name") val name: String? = null,
    /**
     * The year the house was built.
     */
    @JsonProperty("year") val year: Int? = null,
    /**
     * The number of floors in the house.
     */
    @JsonProperty("numberOfFloors") val numberOfFloors: Long? = null,
)
