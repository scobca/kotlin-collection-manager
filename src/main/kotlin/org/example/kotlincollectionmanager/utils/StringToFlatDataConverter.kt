package org.example.kotlincollectionmanager.utils

import org.example.kotlincollectionmanager.collection.items.Furnish
import org.example.kotlincollectionmanager.command.validators.dto.FlatData
import org.springframework.stereotype.Component

/**
 * An object for converting a string to a FlatData object.
 * Provides verification of the input data format and creation of an object with the required fields.
 */
@Component
object StringToFlatDataConverter {
    /**
     * Converts a string to a FlatData object.
     * Splits the input string Divides the input string into values by comma and checks the number of values (it should be 11).
     * If the format is incorrect, returns an object with default values.
     *
     * @param stringifyData is a string containing the data to be converted.
     * @return A FlatData object created from the input string.
     */
    fun convertStringToFlatData(stringifyData: String): FlatData {
        val values = stringifyData
            .replace("[", "")
            .replace("]", "")
            .split(",")

        if (values.size != 11) {
            println("Incorrect data format. Check you script file")
            return FlatData(
                name = "Default name",
                cordX = 0L,
                cordY = 0f,
                area = 1L,
                numberOfRooms = 1L,
                price = 1L,
                balcony = false,
                furnish = Furnish.NONE,
                houseName = "Default house name",
                year = 0,
                numberOfFloors = 0L,
            )
        } else {
            return FlatData(
                name = values[0],
                cordX = values[1].toLongOrNull() ?: 0L,
                cordY = values[2].toFloatOrNull() ?: 0f,
                area = values[3].toLongOrNull() ?: 0L,
                numberOfRooms = values[4].toLongOrNull() ?: 0L,
                price = values[5].toLongOrNull() ?: 0L,
                balcony = values[6].toBoolean(),
                furnish = Furnish.validate(values[7]),
                houseName = values[8],
                year = values[9].toIntOrNull() ?: 0,
                numberOfFloors = values[10].toLongOrNull() ?: 0L,
            )
        }
    }
}