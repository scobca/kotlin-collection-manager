package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.annotation.JsonProperty

data class FlatJson(
    @JsonProperty("id") val id: Long? = null,
    @JsonProperty("name") val name: String? = null,
    @JsonProperty("coordinates") val coordinates: CoordinatesJson? = null,
    @JsonProperty("area") val area: Long? = null,
    @JsonProperty("numberOfRooms") val numberOfRooms: Long? = null,
    @JsonProperty("price") val price: Long? = null,
    @JsonProperty("balcony") val balcony: Boolean? = null,
    @JsonProperty("furnish") val furnish: String? = null,
    @JsonProperty("house") val house: HouseJson? = null,
)
