package advent_kotlin.domain

import java.lang.Math.abs

class RopeBridge(val positions: MutableList<Pair<Int, Int>>) {
    var allTailPosition: MutableList<Pair<Int, Int>> = mutableListOf()

    init {
        allTailPosition.add(positions.last())
    }

    fun moveUp() {
        positions[0] = Pair(positions[0].first, positions[0].second + 1)
        updateAllPositions()
    }

    fun updateAllPositions() {
        var previousPosition = positions[0]
        for (positionIndex in 1 until positions.size) {
            val newPosition = updateFollowingPosition(positions[positionIndex], previousPosition)
            positions[positionIndex] = newPosition
            previousPosition = newPosition
        }
        allTailPosition.add(previousPosition)
    }

    fun moveDown() {
        positions[0] = Pair(positions[0].first, positions[0].second - 1)
        updateAllPositions()
    }

    fun moveLeft() {
        positions[0] = Pair(positions[0].first - 1, positions[0].second)
        updateAllPositions()
    }

    fun moveRight() {
        positions[0] = Pair(positions[0].first + 1, positions[0].second)
        updateAllPositions()
    }

    fun updateFollowingPosition(movingKnot: Pair<Int, Int>, previousKnot: Pair<Int, Int>): Pair<Int, Int> {
        var result = movingKnot
        if (abs(movingKnot.first - previousKnot.first) == 2 && abs(movingKnot.second - previousKnot.second) == 2) {
            result = Pair(
                previousKnot.first + (movingKnot.first - previousKnot.first) / 2,
                previousKnot.second + (movingKnot.second - previousKnot.second) / 2
            )
        } else {
            if (movingKnot.first == previousKnot.first - 2) {
                result = Pair(previousKnot.first - 1, previousKnot.second)
            }
            if (movingKnot.first == previousKnot.first + 2) {
                result = Pair(previousKnot.first + 1, previousKnot.second)
            }
            if (movingKnot.second == previousKnot.second - 2) {
                result = Pair(previousKnot.first, previousKnot.second - 1)
            }
            if (movingKnot.second == previousKnot.second + 2) {
                result = Pair(previousKnot.first, previousKnot.second + 1)
            }
        }
        return result
    }

    fun applyMoves(moves: List<String>) {
        moves.forEach { move ->
            val direction = move.split(" ")[0]
            val numberOfTimes = move.split(" ")[1].toInt()
            for (i in 0 until numberOfTimes) {
                when (direction) {
                    "R" -> moveRight()
                    "L" -> moveLeft()
                    "U" -> moveUp()
                    else -> moveDown()
                }
            }
        }
    }

    fun numberOfUniqueTailPosition(): Int {
        return allTailPosition.toSet().size
    }
}

