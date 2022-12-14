package advent_kotlin.infrastructure

import CommunicationSystem
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

    fun readFileSystem(): Folder {
        val folder = Folder(mutableListOf(), mutableListOf(), "/")
        var currentFolderName = "/"
        var totalSize = 0
        File(fileName).forEachLine { line ->
            // println(line)
            if ("$ cd" in line && ".." !in line) {
                if ("/" !in line) {
                    currentFolderName += line.subSequence(5, line.length).toString() + "/"
                    // println("Moving into " + currentFolderName)
                }
            }
            if ("dir " in line) {
                val newFolderName = currentFolderName + line.subSequence(4, line.length).toString() + "/"
                // println("Creating " + newFolderName + " in "+ currentFolderName)
                folder.getFolder(currentFolderName)
                    .addSubFolder(Folder(mutableListOf(), mutableListOf(), newFolderName))
            }
            if (line[0] in "1234567890") {
                val fileName = line.split(" ")[1]
                val fileSize = line.split(" ")[0].toInt()
                totalSize += fileSize
                // println("Creating file " + fileName + " of size " + fileSize + " in " + currentFolderName)
                folder.getFolder(currentFolderName).addFile(File(currentFolderName + fileName, fileSize))
            }
            if (".." in line) {
                val allFolder = currentFolderName.split("/")
                currentFolderName = java.lang.String.join("/", allFolder.subList(0, allFolder.size - 2)) + "/"
                // println("Mooving back into " + currentFolderName)
            }
        }
        println("Total file size " + totalSize)
        return folder
    }

    fun readForest(): Forest {
        val forest = mutableListOf<List<Int>>()
        File(fileName).forEachLine { line ->
            val elements = line.chunked(1).map { it.toInt() }
            forest.add(elements)
        }
        return Forest(forest)
    }

    fun readRopeMoves(): List<String> {
        val moves = mutableListOf<String>()
        File(fileName).forEachLine { line ->
            moves.add(line)
        }
        return moves
    }

    fun readCommunicationSystem(): CommunicationSystem {
        val instructions = mutableListOf<String>()
        File(fileName).forEachLine {
            instructions.add(it)
        }
        return CommunicationSystem(instructions)
    }

    fun readMonkeys(): Monkeys {
        val monkeyList = mutableListOf<Monkey>()
        var startingItems = listOf<Int>()
        var operation = ""
        var test = 0
        var targetTrue = 0
        var targetFalse = 0
        File(fileName).forEachLine { line ->
            if ("Monkey :" in line) {
                startingItems = listOf()
            }
            if ("Starting items:" in line) {
                startingItems =
                    line.subSequence("  Starting items: ".length, line.length).split(", ").map { it.toInt() }
            }
            if ("Operation: " in line) {
                operation = line
            }
            if ("Test: divisible by " in line) {
                test = line.subSequence("  Test: divisible by ".length, line.length).toString().toInt()
            }
            if ("    If true: throw to monkey " in line) {
                targetTrue = line.subSequence("    If true: throw to monkey ".length, line.length).toString().toInt()
            }
            if ("If false: throw to monkey" in line) {
                targetFalse = line.subSequence("    If false: throw to monkey ".length, line.length).toString().toInt()
            }
            if (line == "") {
                monkeyList.add(Monkey(startingItems.map {it.toBigInteger()}.toMutableList(), operation, test.toBigInteger(), targetTrue, targetFalse))
            }

        }
        monkeyList.add(Monkey(startingItems.map {it.toBigInteger()}.toMutableList(), operation, test.toBigInteger(), targetTrue, targetFalse))
        return Monkeys(monkeyList)
    }
}

fun buildSectionFromString(sectionString: String) =
    Section(sectionString.split("-")[0].toInt(), sectionString.split("-")[1].toInt())