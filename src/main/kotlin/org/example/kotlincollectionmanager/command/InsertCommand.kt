package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.collection.items.Coordinates
import org.example.kotlincollectionmanager.collection.items.Flat
import org.example.kotlincollectionmanager.collection.items.Furnish
import org.example.kotlincollectionmanager.collection.items.House
import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.OneArgCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.example.kotlincollectionmanager.utils.AdvancedScanner
import org.example.kotlincollectionmanager.utils.CommandsErrorHandler
import org.springframework.stereotype.Component

@Component
class InsertCommand(
    override val validator: OneArgCommandValidator,
    private val receiverService: ReceiverService,
    private val scanner: AdvancedScanner,
    private val commandsErrorHandler: CommandsErrorHandler,
) : Command<OneArgCommandValidator> {
    override val name: String = "insert"
    override val description: String = "Adds a new element with the specified key"
    override val keys: List<String>? = listOf("id")

    override fun execute(vararg args: String?) {
        val id = args.elementAt(0)?.toLongOrNull()

        if (id == null) {
            println("Id format is invalid. Please try again")
        } else if (receiverService.getElementById(id) != null) {
            this.commandsErrorHandler.resolveFlatInsertError(id)
        } else {
            val newFlat = Flat()
            val newCoordinates = Coordinates()
            val newHouse = House()

            newFlat.setId(id)
            newFlat.setName(scanner.cycleScan("Enter element name: ") { it })

            newCoordinates.setX(scanner.cycleScan("Enter coordinate x: ") { it.toLong() })
            newCoordinates.setY(scanner.cycleScan("Enter coordinates y: ") { it.toFloat() })
            newFlat.setCoordinates(newCoordinates)

            while (newFlat.getArea() == null) {
                newFlat.setArea(scanner.cycleScan("Enter flat area: ") { it.toLong() })
            }

            while (newFlat.getNumberOfRooms() == null) {
                newFlat.setNumberOfRooms(scanner.cycleScan("Enter number of rooms: ") { it.toLong() })
            }

            while (newFlat.getPrice() != null) {
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

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}