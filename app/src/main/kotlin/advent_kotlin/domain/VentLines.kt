package advent_kotlin.domain

class VentLines constructor(private val lines: List<VentLine>) {
    fun getMaxX(): Int = this.lines.maxOf { it.getMaxX() }
    fun getMaxY(): Int = this.lines.maxOf { it.getMaxY() }
    fun getNumberOfLine(position: Position): Int {
        var numberOfLines: Int = 0
        this.lines.forEach { line: VentLine ->
            if (line.goThrough(position)) {
                numberOfLines += 1
            }
        }
        return numberOfLines
    }

    fun getNumberOfPositionWithMoreThan2Lines(): Int {
        var numberOfPositionWithMoreThan2Lines = 0
        for (x in 0..this.getMaxX()) {
            for (y in 0..this.getMaxY()) {
                if (this.getNumberOfLine(Position(x, y)) >= 2) {
                    println("Candidate : " + Position(x, y ).toString())
                    numberOfPositionWithMoreThan2Lines += 1
                }
            }
        }
        return numberOfPositionWithMoreThan2Lines
    }
}