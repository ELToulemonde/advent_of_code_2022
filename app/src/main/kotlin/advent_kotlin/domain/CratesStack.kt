package advent_kotlin.domain

class CratesStack(var crates: MutableList<String>) {
    fun removeTopCrate(): String {
        val topCrate = crates[crates.size - 1]
        crates.removeAt(crates.size - 1)
        return topCrate
    }

    fun removeTopCrates(numberOfCrates: Int): List<String> {
        val topCrates: List<String> = crates.subList(crates.size - numberOfCrates, crates.size)
        crates = crates.subList(0, crates.size - numberOfCrates)
        return topCrates
    }

    fun addCrateToTop(crate: String) {
        crates.add(crate)
    }

    fun addCratesToTop(cratesToAdd: List<String>) {
        crates.addAll(cratesToAdd)
    }

    fun addToBottom(crate: String) {
        crates.add(0, crate)
    }
}

class CratesStacks(val cratesStacks: List<CratesStack>) {
    fun applyMove(move: CratesMove) {
        for (moveIteration in 1..move.numberOfCratesToMove) {
            val crateMoving = cratesStacks[move.initialPosition - 1].removeTopCrate()
            cratesStacks[move.destinationPosition - 1].addCrateToTop(crateMoving)
        }
    }

    fun applyMove9001(move: CratesMove) {
        val cratesMoving = cratesStacks[move.initialPosition - 1].removeTopCrates(move.numberOfCratesToMove)
        cratesStacks[move.destinationPosition - 1].addCratesToTop(cratesMoving)
    }

    fun getTopCrate(): String {
        var topCrates = ""
        cratesStacks.forEach { cratesStack ->
            topCrates += cratesStack.crates[cratesStack.crates.size - 1]
        }
        return topCrates
    }
}