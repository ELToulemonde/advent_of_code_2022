package advent_kotlin.domain

import java.lang.Double.POSITIVE_INFINITY

class Folder(
    val subFolders: MutableList<Folder>,
    val subFiles: MutableList<File>,
    val name: String
) {
    fun addSubFolder(subFolder: Folder) {
        subFolders.add(subFolder)
    }

    fun addFile(file: File) {
        subFiles.add(file)
    }

    fun getFolder(path: String): Folder {
        return if (path == this.name) {
            this
        } else {
            (getAllFoldersAndSubFolders().filter { content -> content.name == path })[0]
        }

    }


    private fun getAllSubFiles(): List<File> {
        val allSubFiles = subFiles.toMutableList()
        subFolders.forEach { folder ->
            allSubFiles.addAll(folder.getAllSubFiles())
        }
        return allSubFiles
    }

    fun getAllFoldersAndSubFolders(): List<Folder> {
        val allFoldersAndSubFolders = subFolders.toMutableList()
        subFolders.forEach { folder ->
            allFoldersAndSubFolders.addAll(folder.getAllFoldersAndSubFolders())
        }
        return allFoldersAndSubFolders
    }

    private fun getAllSubFilesSizeSum(): Int {
        val allSubFiles = this.getAllSubFiles()
        var allSubFilesSum = 0
        allSubFiles.forEach { file ->
            allSubFilesSum += file.size
        }
        return allSubFilesSum
    }

    private fun findAllFoldersWithLowerSizeThan(maxSize: Int): List<Pair<Folder, Int>> {
        val allFolders = getAllFoldersAndSubFolders()
        val foldersWithSizeLowerThan = mutableListOf<Pair<Folder, Int>>()
        allFolders.forEach { folder ->
            val folderSize = folder.getAllSubFilesSizeSum()
            if (folderSize <= maxSize) {
                foldersWithSizeLowerThan.add(Pair(folder, folderSize))
            }
        }
        return foldersWithSizeLowerThan
    }

    fun sumOfSizeOfAllFoldersWithLowerSizeThan(maxSize: Int): Int {
        val foldersWithSizeLowerThan = findAllFoldersWithLowerSizeThan(maxSize)
        var total = 0
        foldersWithSizeLowerThan.forEach { pair ->
            total += pair.second
        }
        return total
    }

    fun getAllFileSystemSize(): Int {
        var totalFileSystemSize = 0
        subFiles.toSet().toList().forEach { file ->
            totalFileSystemSize += file.size
        }
        subFolders.forEach { subFolder ->
            totalFileSystemSize += subFolder.getAllFileSystemSize()
        }
        return totalFileSystemSize
    }

    fun getSpaceToFree(neededSpace: Int, totalSpace: Int): Int = neededSpace - (totalSpace - getAllFileSystemSize())
    fun getMinFolderSizeToDelete(spaceToFree: Int): Int {
        var minSpaceToFree = POSITIVE_INFINITY.toInt()
        val sizeOfThisFolder = getAllSubFilesSizeSum()
        if (sizeOfThisFolder >= spaceToFree) {
            minSpaceToFree = sizeOfThisFolder
        }
        subFolders.forEach { subFolder ->
            val subFolderMin = subFolder.getMinFolderSizeToDelete(spaceToFree)
            if (subFolderMin in spaceToFree until minSpaceToFree) {
                minSpaceToFree = subFolderMin
            }
            val subFolderSpace = subFolder.getAllSubFilesSizeSum()
            if (subFolderSpace in spaceToFree until minSpaceToFree) {
                minSpaceToFree = subFolderSpace
            }
        }
        return minSpaceToFree


    }

    override fun toString(): String = "Name : " + name
}


class File(val name: String, val size: Int) {
    override fun toString(): String = name + ": " + size
}

