package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.annotation.JsonProperty

data class CoordinatesJson(
    @JsonProperty("x") val x: Long? = null,
    @JsonProperty("y") val y: Float? = null,
)
