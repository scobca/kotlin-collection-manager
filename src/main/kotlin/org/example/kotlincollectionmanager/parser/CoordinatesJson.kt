package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A class for representing coordinates in JSON format.
 * Used to deserialize coordinates from a JSON string.
 */
data class CoordinatesJson(
    /**
     * X coordinate.
     */
    @JsonProperty("x") val x: Long? = null,
    /**
     * Y coordinate.
     */
    @JsonProperty("y") val y: Float? = null,
)
