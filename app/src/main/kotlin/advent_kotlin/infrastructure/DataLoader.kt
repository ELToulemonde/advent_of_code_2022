package advent_kotlin.infrastructure

import advent_kotlin.domain.*
import java.io.File

class DataLoader constructor(private val fileName: String) {
    fun readFileLineByLineUsingForEachLine(): VentLines {
        val ventLines: MutableList<VentLine> = mutableListOf();
        File(this.fileName).forEachLine {
            val inputOutputs = it.split(" -> ")
            val inputs = inputOutputs[0].split(",")
            val outputs = inputOutputs[1].split(",")
            ventLines.add(
                VentLine(
                    Position(inputs[0].toInt(), inputs[1].toInt()), Position(outputs[0].toInt(), outputs[1].toInt())
                )
            )
            return@forEachLine
        }
        return VentLines(ventLines)
    }

    fun readFileAndGetCalories(): Int {
        val listCalories = mutableListOf<Int>()
        var currentCount = 0
        File(this.fileName).forEachLine {
            if (it == "") {
                listCalories.add(currentCount)
                currentCount = 0
            } else {
                currentCount += it.toInt()
            }
        }
        listCalories.add(currentCount)

        val sortedListCalories = listCalories.sortedDescending()
        return sortedListCalories[0] + sortedListCalories[1] + sortedListCalories[2]
    }

    fun readStrategies(): List<Strategy> {
        val strategies = mutableListOf<Strategy>()
        File(this.fileName).forEachLine {
            val opponent = it.split(" ")[0]
            val goal = it.split(" ")[1]
            val strategy = Strategy(opponent, getStrategy(opponent, goal))
            strategies.add(strategy)
        }
        return strategies
    }

    fun readRuckStacks(): List<RuckStack> {
        val ruckStacks = mutableListOf<RuckStack>()
        File(this.fileName).forEachLine {
            val elements: List<String> = it.chunked(1)
            val n_elements = elements.size
            val compartmentLength = n_elements / 2
            if (n_elements - compartmentLength * 2 != 0) {
                println(it)
            }
            ruckStacks.add(
                RuckStack(
                    elements.subList(0, compartmentLength), elements.subList(compartmentLength, n_elements)
                )
            )
        }
        return ruckStacks
    }

    fun readSectionPairs(): List<SectionPairs> {
        val sectionPairs = mutableListOf<SectionPairs>()
        File(this.fileName).forEachLine {
            val sections = it.split(",")
            val section_1 = buildSectionFromString(sections[0])
            val section_2 = buildSectionFromString(sections[1])
            sectionPairs.add(SectionPairs(section_1, section_2))
        }
        return sectionPairs
    }

    fun readMoves(): List<CratesMove> {
        val cratesMove = mutableListOf<CratesMove>()
        File(this.fileName).forEachLine {
            if ("move" in it) {
                val move = CratesMove(
                    it.split("move ")[1].split(" from ")[0].toInt(),
                    it.split("from ")[1].split(" to ")[0].toInt(),
                    it.split("to ")[1].toInt(),
                )
                cratesMove.add(move)
            }
        }
        return cratesMove
    }

    fun getNumberOfCrates(): Int {
        var numberOfCrates = 0
        File(fileName).forEachLine {
            if (" 1   2" in it) {
                val maxString = it.split(" ").maxOrNull()
                if (maxString != null) {
                    numberOfCrates = maxString.toInt()
                }

            }
        }
        return numberOfCrates
    }

    fun readCratesStack(): CratesStacks {
        val numberOfCrates = this.getNumberOfCrates()
        val cratesStackList = mutableListOf<CratesStack>()
        for (cratesId in 1..numberOfCrates) {
            cratesStackList.add(CratesStack(mutableListOf()))
        }
        File(fileName).forEachLine {
            if ("[" in it) {
                val crates = it.chunked(1)
                var indexOfCrate = 0
                crates.forEach { crate ->
                    if (crate in "ABCDEFGHIJKLMNOPQRSTUVWXYZ") {
                        val cratesStackNumber = (indexOfCrate - 1) / 4
                        cratesStackList[cratesStackNumber].addToBottom(crate)
                    }
                    indexOfCrate += 1


                }

            }


        }

        return CratesStacks(cratesStackList)

    }
}

fun buildSectionFromString(sectionString: String) =
    Section(sectionString.split("-")[0].toInt(), sectionString.split("-")[1].toInt())