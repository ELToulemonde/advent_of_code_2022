package advent_kotlin.domain

private const val ROCK = "A"
private const val PAPER = "B"
private const val SCISSOR = "C"
private const val LOOSE = "X"
private const val DRAW = "Y"

class Strategy constructor(val opponent: String, val me: String) {
    private fun getMyPoints(): Int {
        return when (me) {
            ROCK -> 1
            PAPER -> 2
            else -> 3
        }
    }

    fun getTotalScore(): Int {
        return this.getGameScore() + this.getMyPoints()
    }

    private fun getGameScore(): Int {
        return when (opponent) {
            ROCK -> {
                when (me) {
                    ROCK -> 3
                    PAPER -> 6
                    else -> 0
                }
            }

            PAPER -> {
                when (me) {
                    ROCK -> 0
                    PAPER -> 3
                    else -> 6
                }
            }

            else -> {
                when (me) {
                    ROCK -> 6
                    PAPER -> 0
                    else -> 3
                }
            }
        }
    }
}


fun getStrategy(opponent: String, goal: String): String {
    return when (opponent) {
        ROCK -> {
            when (goal) {
                LOOSE -> SCISSOR
                DRAW -> ROCK
                else -> PAPER
            }
        }
        PAPER -> {
            when (goal) {
                LOOSE -> ROCK
                DRAW -> PAPER
                else -> SCISSOR
            }
        }
        else -> {
            when (goal) {
                LOOSE -> PAPER
                DRAW -> SCISSOR
                else -> ROCK
            }
        }
    }
}