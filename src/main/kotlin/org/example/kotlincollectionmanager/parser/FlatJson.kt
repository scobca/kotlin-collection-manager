package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Class for representing apartments in JSON format.
 * Used to deserialize apartment data from a JSON string.
 */
data class FlatJson(
    /**
     * Apartment ID.
     */
    @JsonProperty("id") val id: Long? = null,
    /**
     * The name of the apartment.
     */
    @JsonProperty("name") val name: String? = null,
    /**
     * Coordinates of the apartment.
     */
    @JsonProperty("coordinates") val coordinates: CoordinatesJson? = null,
    /**
     * The area of the apartment.
     */
    @JsonProperty("area") val area: Long? = null,
    /**
     * The number of rooms in the apartment.
     */
    @JsonProperty("numberOfRooms") val numberOfRooms: Long? = null,
    /**
     * The price of the apartment.
     */
    @JsonProperty("price") val price: Long? = null,
    /**
     * The presence of a balcony.
     */
    @JsonProperty("balcony") val balcony: Boolean? = null,
    /**
     * The type of furniture in the apartment.
     */
    @JsonProperty("furnish") val furnish: String? = null,
    /**
     * Information about the house where the apartment is located.
     */
    @JsonProperty("house") val house: HouseJson? = null,
)
