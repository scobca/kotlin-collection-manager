package org.example.kotlincollectionmanager.collection.items

class Coordinates(private var x: Long? = null, private var y: Float? = null) {
    fun getX(): Long? = x
    fun getY(): Float? = y

    fun setX(x: Long) = apply { this.x = x }
    fun setY(y: Float) = apply { this.y = y }

    override fun toString(): String {
        return "Coordinates(x=$x, y=$y)"
    }
}