package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.kotlincollectionmanager.collection.items.Coordinates
import org.example.kotlincollectionmanager.collection.items.Flat
import org.example.kotlincollectionmanager.collection.items.Furnish
import org.example.kotlincollectionmanager.collection.items.House
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component
import java.io.InputStream
import java.io.InputStreamReader

@Component
class JsonParser(private val receiverService: ReceiverService) {
    fun loadFlats(inputStream: InputStream) {
        val mapper = ObjectMapper()

        try {
            InputStreamReader(inputStream).use { reader ->
                val flatsJson: Array<FlatJson> = mapper.readValue(reader, Array<FlatJson>::class.java)

                flatsJson.forEach { flat ->
                    val newFlat = Flat()

                    flat.id?.let { newFlat.setId(it) }
                    flat.name?.let { newFlat.setName(it) }
                    flat.coordinates?.let { newFlat.setCoordinates(Coordinates(it.x, it.y)) }
                    flat.area?.let { newFlat.setArea(it) }
                    flat.price?.let { newFlat.setPrice(it) }
                    flat.balcony?.let { newFlat.setBalcony(it) }
                    flat.furnish?.let { Furnish.valueOf(it) }?.let { newFlat.setFurnish(it) }
                    flat.house?.let { House(it.name, it.year, it.numberOfFloors) }?.let { newFlat.setHouse(it) }

                    receiverService.insert(newFlat)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}