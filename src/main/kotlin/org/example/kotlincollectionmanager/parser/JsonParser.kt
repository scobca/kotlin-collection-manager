package org.example.kotlincollectionmanager.parser

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.example.kotlincollectionmanager.collection.items.Coordinates
import org.example.kotlincollectionmanager.collection.items.Flat
import org.example.kotlincollectionmanager.collection.items.Furnish
import org.example.kotlincollectionmanager.collection.items.House
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.stereotype.Component
import java.io.*
import java.util.*

/**
 * A parser for working with JSON files.
 * Provides methods for downloading and saving flat data in JSON format.
 */
@Component
class JsonParser(
    /**
     * A service for interacting with a collection of objects.
     */
    private val receiverService: ReceiverService
) {
    /**
     * Downloads flats from a JSON file and adds them to the collection.
     *
     * @param inputStream is a stream for reading JSON data.
     */
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
        } catch (e: InvalidFormatException) {
            println("File was damaged. Please, check it and try again")
        } catch (e: Exception) {
            println("Unexpected error, please try again")
        }
    }

    /**
     * Saves flats from the collection to a JSON file.
     *
     * @param flats flat map to save.
     * @param fileName is the name of the file to save (with the extension ".json", if there is none).
     */
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
            println("Unexpected error, please try again. Cause: ${e.message}")
        }
    }
}