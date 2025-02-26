package org.example.kotlincollectionmanager.command.validators.dto

import org.example.kotlincollectionmanager.collection.items.Furnish

data class FlatData(
    val name: String,
    val cordX: Long,
    val cordY: Float,
    val area: Long,
    val numberOfRooms: Long,
    val price: Long,
    val balcony: Boolean,
    val furnish: Furnish,
    val houseName: String,
    val year: Int,
    val numberOfFloors: Long,
)