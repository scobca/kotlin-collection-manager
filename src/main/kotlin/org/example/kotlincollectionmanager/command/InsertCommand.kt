package org.example.kotlincollectionmanager.command

import org.example.kotlincollectionmanager.collection.items.Coordinates
import org.example.kotlincollectionmanager.collection.items.Flat
import org.example.kotlincollectionmanager.collection.items.Furnish
import org.example.kotlincollectionmanager.collection.items.House
import org.example.kotlincollectionmanager.command.intefaces.Command
import org.example.kotlincollectionmanager.command.validators.NoArgsCommandValidator
import org.example.kotlincollectionmanager.receiver.ReceiverService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class InsertCommand(
    @Qualifier("noArgsCommandValidator") override val validator: NoArgsCommandValidator,
    private val receiverService: ReceiverService
) : Command<NoArgsCommandValidator> {
    override val name: String = "insert"
    override val description: String = "Adds a new element with the specified key"
    override val keys: List<String>? = null

    override fun execute(vararg args: String?) {
        val flat = Flat(
            name = "flat",
            coordinates = Coordinates(10L, 1F),
            area = 100L,
            numberOfRooms = 10L,
            price = 100000L,
            balcony = true,
            furnish = Furnish.DESIGNER,
            house = House(
                name = "house",
                year = 2016,
                numberOfFloors = 10L
            ),
        )
        receiverService.insert(flat)
        println("Flat added successfully")
    }

    override fun validate(args: List<String>) {
        validator.validateArgs(args, this)
    }
}