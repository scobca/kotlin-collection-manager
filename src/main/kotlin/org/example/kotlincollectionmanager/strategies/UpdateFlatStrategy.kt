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

@Component
class UpdateFlatStrategy(
    private val receiverService: ReceiverService,
    private val commandsErrorHandler: CommandsErrorHandler,
    private val scanner: AdvancedScanner
) : SpecifyCommandStrategy {
    override fun lifeTimeInsert(id: Long?) {
        if (id == null) {
            println("Id format is invalid. Please try again")
        } else if (receiverService.getElementById(id) == null) {
            commandsErrorHandler.resolveFlatUpdateError(id)
        } else {
            val flat = receiverService.getElementById(id) as Flat
            val coordinates = flat.getCoordinates() as Coordinates
            val house = flat.getHouse() as House

            scanner.cycleUpdateScan("Enter new element name (old name: ${flat.getName()}): ", flat.getName()) { it }
                ?.let { flat.setName(it) }

            scanner.cycleUpdateScan(
                "Enter new coordinate x (old coordinates: ${coordinates.getX()}): ",
                coordinates.getX()
            ) { it.toLong() }
                ?.let { coordinates.setX(it) }

            scanner.cycleUpdateScan(
                "Enter coordinates y (old coordinates: ${coordinates.getY()}): ",
                coordinates.getY()
            ) { it.toFloat() }
                ?.let { coordinates.setY(it) }
            flat.setCoordinates(coordinates)

            scanner.cycleUpdateScan(
                "Enter new flat area (old area: ${flat.getArea()}): ",
                flat.getArea()
            ) { it.toLong() }?.let { flat.setArea(it) }

            scanner.cycleUpdateScan(
                "Enter new number of rooms (old number of rooms: ${flat.getNumberOfRooms()}): ",
                flat.getNumberOfRooms()
            ) { it.toLong() }
                ?.let { flat.setNumberOfRooms(it) }

            scanner.cycleUpdateScan(
                "Enter new price (old price: ${flat.getPrice()}): ",
                flat.getPrice()
            ) { it.toLong() }
                ?.let { flat.setPrice(it) }

            scanner.cycleUpdateScan(
                "Enter new furnish type (DESIGNER | FINE | LITTLE) (old furnish type: ${flat.getFurnish()}): ",
                flat.getFurnish()
            ) {
                try {
                    Furnish.valueOf(it.uppercase().trim())
                } catch (e: IllegalArgumentException) {
                    throw IllegalArgumentException("Invalid furnish type. Please try again.")
                }
            }?.let { flat.setFurnish(it) }

            scanner.cycleUpdateScan(
                "Enter new house name (old house name: ${house.getName()}): ",
                house.getName()
            ) { it }
                ?.let { house.setName(it) }

            scanner.cycleUpdateScan(
                "Enter new year of building a house (old year of building: ${house.getYear()}): ",
                house.getYear()
            ) { it.toInt() }
                ?.let { house.setYear(it) }

            scanner.cycleUpdateScan(
                "Enter new number of floors (old nu,ber of floors: ${house.getNumberOfFloors()}): ",
                house.getNumberOfFloors()
            ) { it.toLong() }
                ?.let { house.setNumberOfFloors(it) }
            flat.setHouse(house)

            receiverService.update(flat)
        }
    }

    override fun automaticallyInsert(id: Long?, data: FlatData) {
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