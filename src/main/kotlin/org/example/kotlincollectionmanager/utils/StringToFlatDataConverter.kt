package org.example.kotlincollectionmanager.utils

import org.example.kotlincollectionmanager.collection.items.Furnish
import org.example.kotlincollectionmanager.command.validators.dto.FlatData
import org.springframework.stereotype.Component

@Component
object StringToFlatDataConverter {
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
                area = 0L,
                numberOfRooms = 0L,
                price = 0L,
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