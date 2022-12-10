class CommunicationSystem(val listInstructions: List<String>) {
    fun getSignalAtCycle(cycle: Int): Int {
        var cycleNumber = 0
        var signal = 1
        var numberOfInstructionsRead = 0
        var addxStartd = false
        while (cycleNumber < cycle - 1) {
            val instruction = listInstructions[numberOfInstructionsRead]
            if ("noop" in instruction) {
                numberOfInstructionsRead += 1
            }
            if ("addx" in instruction) {
                if (!addxStartd) {
                    addxStartd = true
                } else {
                    addxStartd = false
                    signal += instruction.split(" ")[1].toInt()
                    numberOfInstructionsRead += 1
                }
            }
            cycleNumber += 1
        }
        return signal
    }

    fun draw(): List<List<String>> {
        val screen = listOf(
            mutableListOf(),
            mutableListOf<String>(),
            mutableListOf<String>(),
            mutableListOf<String>(),
            mutableListOf<String>(),
            mutableListOf<String>(),
        )
        for (cycle in 1..240) {
            val row = (cycle -1) / 40
            val col = (cycle -1) % 40
            val spire = getSignalAtCycle(cycle)
            if (col in spire-1..spire+1){
                screen[row].add("#")
            }
            else{
                screen[row].add(".")
            }

        }
        return screen
    }
}