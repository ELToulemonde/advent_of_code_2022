package advent_kotlin.domain

import java.lang.Math.abs

class VentLine constructor(private val start: Position, private val end: Position) {
    fun getMaxX(): Int = maxOf(this.start.x, this.end.x)
    private fun getMinX(): Int = minOf(this.start.x, this.end.x)
    fun getMaxY(): Int = maxOf(this.start.y, this.end.y)
    private fun getMinY(): Int = minOf(this.start.y, this.end.y)
    private fun isHorizontalLine() = this.start.x == this.end.x
    private fun isVerticalLine() = this.start.y == this.end.y


    override fun toString(): String = this.start.toString() + " -> " + this.end.toString()

    fun goThrough(position: Position): Boolean {
        if (this.isHorizontalLine() || this.isVerticalLine()) {
            return this.isBetweenLineRange(position)
        } else if (this.isDiagonalLine()) {
            return this.isOnDiagonalLine(position)
        }
        return false
    }

    private fun isBetweenLineRange(position: Position): Boolean {
        return this.getMinX() <= position.x && position.x <= this.getMaxX() && this.getMinY() <= position.y && position.y <= this.getMaxY()
    }

    private fun isOnDiagonalLine(position: Position): Boolean {
        val positionIsDiagonalWithStart =
            kotlin.math.abs(this.start.x - position.x) == kotlin.math.abs(this.start.y - position.y)
        val positionIsDiagonalWithEnd =
            kotlin.math.abs(position.x - this.end.x) == kotlin.math.abs(position.y - this.end.y)
        return positionIsDiagonalWithStart && positionIsDiagonalWithEnd && this.isBetweenLineRange(position)
    }

    fun isDiagonalLine(): Boolean {
        return kotlin.math.abs(this.start.x - this.end.x) == kotlin.math.abs(this.start.y - this.end.y)
    }
}