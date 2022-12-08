package advent_kotlin.domain

class Forest(val forest: List<List<Int>>) {

    fun numberOfVisibleTrees(): Int {
        var numberOfVisibleTrees = 0
        for (treeRow in forest.indices) {
            for (treeCol in forest.indices) {
                if (isTreeVisible(treeRow, treeCol)) {
                    numberOfVisibleTrees += 1
                }
            }
        }
        return numberOfVisibleTrees
    }

    fun isTreeVisible(treeRow: Int, treeCol: Int): Boolean {
        return isVisibleFromLeft(treeCol, treeRow) || isVisibleFromRight(treeCol, treeRow) || isVisibleFromTop(
            treeCol,
            treeRow
        ) || isVisibleFromBottom(treeCol, treeRow)
    }

    fun isVisibleFromBottom(treeCol: Int, treeRow: Int): Boolean {
        val colFromBottom = mutableListOf<Int>()
        for (row in treeRow + 1 until forest.size) {
            colFromBottom.add(forest[row][treeCol])
        }
        val isVisibleFromBottom =
            treeRow == forest.size - 1 || (colFromBottom.maxOrNull() ?: 0) < forest[treeRow][treeCol]
        return isVisibleFromBottom
    }

    fun isVisibleFromTop(treeCol: Int, treeRow: Int): Boolean {
        val colFromTop = mutableListOf<Int>()
        for (row in 0 until treeRow) {
            colFromTop.add(forest[row][treeCol])
        }
        val isVisibleFromTop = treeRow == 0 || (colFromTop.maxOrNull() ?: 0) < forest[treeRow][treeCol]
        return isVisibleFromTop
    }

    fun isVisibleFromRight(treeCol: Int, treeRow: Int): Boolean {
        return if (treeCol == forest.size - 1) {
            true
        } else {
            val isVisibleFromRight = (forest[treeRow].subList(treeCol + 1, forest.size).maxOrNull()
                ?: 0) < forest[treeRow][treeCol]
            isVisibleFromRight
        }

    }

    fun isVisibleFromLeft(treeCol: Int, treeRow: Int): Boolean {
        val isVisibleFromLeft =
            treeCol == 0 || (forest[treeRow].subList(0, treeCol).maxOrNull() ?: 0) < forest[treeRow][treeCol]
        return isVisibleFromLeft
    }

    fun scenicScore(treeCol: Int, treeRow: Int): Int {
        val topScenicScore = if (treeRow != 0) {
            val treeList = mutableListOf<Int>()
            for (row in 0 until treeRow) {
                treeList.add(forest[row][treeCol])
            }
            nViewAbleTree(treeList.reversed(), forest[treeRow][treeCol])
        } else {
            0
        }
        val bottomScenicScore = if (treeRow != forest.size - 1) {
            val treeList = mutableListOf<Int>()
            for (row in treeRow + 1 until forest.size) {
                treeList.add(forest[row][treeCol])
            }
            nViewAbleTree(treeList, forest[treeRow][treeCol])
        } else {
            0
        }
        val leftScenicScore = if (treeCol != 0) {
            nViewAbleTree(forest[treeRow].subList(0, treeCol).reversed(), forest[treeRow][treeCol])
        } else {
            0
        }
        val rightScenicScore = if (treeCol != forest.size - 1) {
            nViewAbleTree(forest[treeRow].subList(treeCol + 1, forest.size), forest[treeRow][treeCol])
        } else {
            0
        }
        return topScenicScore * bottomScenicScore * leftScenicScore * rightScenicScore
    }

    fun maxScenicScore(): Int {
        var maxScenicScore = 0
        for (treeRow in forest.indices) {
            for (treeCol in forest.indices) {
                val thisScenicScore = scenicScore(treeCol, treeRow)
                if (thisScenicScore > maxScenicScore)
                    maxScenicScore = thisScenicScore
            }
        }

        return maxScenicScore
    }

}

fun nViewAbleTree(listTree: List<Int>, treeHeight: Int): Int {
    var notBlocked = true
    var treeIndex = 0
    var nViewable = 0
    while (notBlocked && treeIndex < listTree.size) {
        nViewable += 1
        if (listTree[treeIndex] >= treeHeight) {
            notBlocked = false
        }
        treeIndex += 1
    }
    return nViewable
}