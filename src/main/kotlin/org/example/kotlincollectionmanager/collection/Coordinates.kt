package org.example.kotlincollectionmanager.collection

class Coordinates(private var x: Long, private var y: Long) {
    fun getX(): Long = x
    fun getY(): Long = y

    override fun toString(): String {
        return "Coordinates(x=$x, y=$y)"
    }
}