package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Class for representing flats in JSON format.
 * Used to deserialize flat data from a JSON string.
 */
data class FlatJson(
    /**
     * flat ID.
     */
    @JsonProperty("id") val id: Long? = null,
    /**
     * The name of the flat.
     */
    @JsonProperty("name") val name: String? = null,
    /**
     * Coordinates of the flat.
     */
    @JsonProperty("coordinates") val coordinates: CoordinatesJson? = null,
    /**
     * The area of the flat.
     */
    @JsonProperty("area") val area: Long? = null,
    /**
     * The number of rooms in the flat.
     */
    @JsonProperty("numberOfRooms") val numberOfRooms: Long? = null,
    /**
     * The price of the flat.
     */
    @JsonProperty("price") val price: Long? = null,
    /**
     * The presence of a balcony.
     */
    @JsonProperty("balcony") val balcony: Boolean? = null,
    /**
     * The type of furniture in the flat.
     */
    @JsonProperty("furnish") val furnish: String? = null,
    /**
     * Information about the house where the flat is located.
     */
    @JsonProperty("house") val house: HouseJson? = null,
)
