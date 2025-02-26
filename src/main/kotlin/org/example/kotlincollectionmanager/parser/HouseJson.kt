package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.annotation.JsonProperty

data class HouseJson(
    @JsonProperty("name") val name: String? = null,
    @JsonProperty("year") val year: Int? = null,
    @JsonProperty("numberOfFloors") val numberOfFloors: Long? = null,
)
