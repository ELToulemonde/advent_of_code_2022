package advent_kotlin.domain

class Position constructor(val x: Int, val y: Int) {
    override fun toString() = this.x.toString() + ", " + this.y.toString()
}