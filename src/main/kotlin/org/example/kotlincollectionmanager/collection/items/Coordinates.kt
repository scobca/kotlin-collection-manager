package org.example.kotlincollectionmanager.collection.items

class Coordinates(private var x: Long, private var y: Float) {
    fun getX(): Long = x
    fun getY(): Float = y

    override fun toString(): String {
        return "Coordinates(x=$x, y=$y)"
    }
}