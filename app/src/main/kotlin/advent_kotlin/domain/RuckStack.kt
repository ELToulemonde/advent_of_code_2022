package advent_kotlin.domain

class RuckStack(val compartment1: List<String>, val compartment2: List<String>) {
    fun getSharedElementsInCompartments() = compartment1.intersect(compartment2)
    override fun toString() = compartment1.toString() + " " + compartment2.toString()
    fun getAllItems(): List<String> = compartment1 + compartment2
}

const val alphabet = "abcdefghijklmnopqrstuvwxyz";
fun getLetterPriority(letter: String): Int {
    return if (letter.toCharArray()[0].isLowerCase()) {
        alphabet.indexOf(letter) + 1
    } else {
        alphabet.indexOf(letter.lowercase()) + 26 + 1
    }
}

class ElfGroup(val ruckStack1: RuckStack, val ruckStack2: RuckStack, val ruckStack3: RuckStack) {
    fun getBadge(): String = (ruckStack1.getAllItems()).intersect(ruckStack2.getAllItems()).intersect(ruckStack3.getAllItems()).first()
}