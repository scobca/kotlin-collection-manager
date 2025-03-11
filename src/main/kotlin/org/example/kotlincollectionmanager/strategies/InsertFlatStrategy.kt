package org.example.kotlincollectionmanager.strategies

import org.example.kotlincollectionmanager.collection.items.Coordinates
import org.example.kotlincollectionmanager.collection.items.Flat
import org.example.kotlincollectionmanager.collection.items.Furnish
import org.example.kotlincollectionmanager.collection.items.House
import org.example.kotlincollectionmanager.command.validators.dto.FlatData
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.example.kotlincollectionmanager.strategies.interfaces.SpecifyCommandStrategy
import org.example.kotlincollectionmanager.utils.AdvancedScanner
import org.example.kotlincollectionmanager.utils.CommandsErrorHandler
import org.springframework.stereotype.Component

/**
 * A strategy for inserting an flat into a collection.
 * Implements the SpecifyCommandStrategy interface and uses the ReceiverService to interact with the collection.
 */
@Component
class InsertFlatStrategy(
    /**
     * A service for interacting with a collection of objects.
     */
    private val receiverService: ReceiverService,
    /**
     * Error handler for commands.
     */
    private val commandsErrorHandler: CommandsErrorHandler,
    /**
     * Advanced scanner for reading user input.
     */
    private val scanner: AdvancedScanner,
) : SpecifyCommandStrategy {
    /**
     * Performs manual insertion of the flat by the specified identifier.
     *
     * @param id is the ID of the flat to insert.
     */
    override fun lifeTimeExecution(id: Long?) {
        if (id == null) {
            println("Id format is invalid. Please try again")
        } else if (receiverService.getElementById(id) != null) {
            this.commandsErrorHandler.resolveFlatInsertError(id)
        } else {
            val newFlat = Flat()
            val newCoordinates = Coordinates()
            val newHouse = House()

            newFlat.setId(id)

            while (newFlat.getName() == null) {
                newFlat.setName(scanner.cycleScan("Enter element name: ") { it }.trim())
            }

            newCoordinates.setX(scanner.cycleScan("Enter coordinate x: ") { it.toLong() })
            newCoordinates.setY(scanner.cycleScan("Enter coordinates y: ") { it.toFloat() })
            newFlat.setCoordinates(newCoordinates)

            while (newFlat.getArea() == null) {
                newFlat.setArea(scanner.cycleScan("Enter flat area: ") { it.toLong() })
            }

            while (newFlat.getNumberOfRooms() == null) {
                newFlat.setNumberOfRooms(scanner.cycleScan("Enter number of rooms: ") { it.toLong() })
            }

            while (newFlat.getPrice() == null) {
                newFlat.setPrice(scanner.cycleScan("Enter price: ") { it.toLong() })
            }

            newFlat.setBalcony(scanner.cycleScanBoolean("Enter balcony available (true/false): "))

            newFlat.setFurnish(scanner.cycleScan("Enter furnish type (DESIGNER, FINE, LITTLE): ") {
                try {
                    Furnish.valueOf(it.uppercase().trim())
                } catch (e: IllegalArgumentException) {
                    throw IllegalArgumentException("Invalid furnish type. Please try again.")
                }
            })

            newHouse.setName(scanner.cycleScan("Enter new house name: ") { it })
            newHouse.setYear(scanner.cycleScan("Enter the year of building a house: ") { it.toInt() })
            newHouse.setNumberOfFloors(scanner.cycleScan("Enter the number of floors: ") { it.toLong() })
            newFlat.setHouse(newHouse)

            receiverService.insert(newFlat)
        }
    }

    /**
     * Performs automatic flat insertion based on the specified data.
     *
     * @param id is the ID of the flat to insert.
     * @param data flat data to insert.
     */
    override fun automaticallyExecution(id: Long?, data: FlatData) {
        val coordinates = Coordinates()
            .setX(data.cordX)
            .setY(data.cordY)

        val house = House()
            .setName(data.houseName)
            .setYear(data.year)
            .setNumberOfFloors(data.numberOfFloors)

        val flat = id?.let {
            Flat()
                .setId(it)
                .setName(data.name)
                .setCoordinates(coordinates)
                .setArea(data.area)
                .setNumberOfRooms(data.numberOfRooms)
                .setPrice(data.price)
                .setBalcony(data.balcony)
                .setFurnish(data.furnish)
                .setHouse(house)
        }

        flat?.let { receiverService.insert(it) }
    }
}