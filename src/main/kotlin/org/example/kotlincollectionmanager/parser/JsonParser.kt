package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.kotlincollectionmanager.collection.items.Coordinates
import org.example.kotlincollectionmanager.collection.items.Flat
import org.example.kotlincollectionmanager.collection.items.Furnish
import org.example.kotlincollectionmanager.collection.items.House
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component
import java.io.*
import java.util.*

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

    fun saveFlats(flats: TreeMap<Long, Flat>, fileName: String) {
        val mapper = ObjectMapper()

        try {
            val file = if (fileName.contains(".json")) {
                File(fileName)
            } else {
                File("$fileName.json")
            }

            if (file.exists()) file.delete()
            file.createNewFile()

            val flatsJson = flats.values.map { flat ->
                FlatJson(
                    id = flat.getId(),
                    name = flat.getName(),
                    coordinates = flat.getCoordinates()?.let { CoordinatesJson(it.getX(), it.getY()) },
                    area = flat.getArea(),
                    price = flat.getPrice(),
                    balcony = flat.getBalcony(),
                    furnish = flat.getFurnish()?.name,
                    house = flat.getHouse()?.let { HouseJson(it.getName(), it.getYear(), it.getNumberOfFloors()) }
                )
            }.toTypedArray()

            OutputStreamWriter(FileOutputStream(file)).use { out ->
                mapper.writeValue(out, flatsJson)
            }
            println("Квартиры успешно сохранены в файл $fileName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}