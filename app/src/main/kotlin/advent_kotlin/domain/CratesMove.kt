package advent_kotlin.domain

class CratesMove(val numberOfCratesToMove: Int, val initialPosition: Int, val destinationPosition: Int) {
    override fun toString(): String {
        return "move " + numberOfCratesToMove.toString() + " from " + initialPosition.toString() + " to " + destinationPosition.toString()
    }
}