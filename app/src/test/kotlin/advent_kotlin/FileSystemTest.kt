package advent_kotlin

import advent_kotlin.domain.File
import advent_kotlin.domain.Folder
import org.junit.Test
import kotlin.test.assertEquals

class FileSystemTest {
    @Test
    fun getAllFileSystemSizeEmptyFileSystem() {
        // Given
        val fileSystem = Folder(mutableListOf(), mutableListOf(), name = "/")

        // When
        val fileSystemSize = fileSystem.getAllFileSystemSize()

        // Then
        assertEquals(fileSystemSize, 0)

    }

    @Test
    fun getAllFileSystemSizeWhenOneSubFolder() {
        // Given
        val fileSystem = Folder(
            mutableListOf(Folder(mutableListOf(), mutableListOf(File("c.txt", 11)), name = "/b/")),
            mutableListOf(File("a.txt", 10)),
            name = "/"
        )

        // When
        val fileSystemSize = fileSystem.getAllFileSystemSize()

        // Then
        assertEquals(fileSystemSize, 21)
    }

    @Test
    fun getAllFileSystemSizeWhenTwoNestedSubFolder() {
        // Given
        val fileSystem = givenFileSystem()

        // When
        val fileSystemSize = fileSystem.getAllFileSystemSize()

        // Then
        assertEquals(fileSystemSize, 46)
    }

    private fun givenFileSystem(): Folder {
        val fileSystem = Folder(
            mutableListOf(
                Folder(
                    mutableListOf(
                        Folder(
                            mutableListOf(), mutableListOf(
                                File("/b/b/d.txt", 12),
                                File("/b/b/c.txt", 13)
                            ), name = "/b/b/"
                        )
                    ),
                    mutableListOf(File("/b/c.txt", 11)),
                    name = "/b/"
                )
            ),
            mutableListOf(File("/a.txt", 10)),
            name = "/"
        )
        return fileSystem
    }

    @Test
    fun getAllFoldersAndSubFolders(){
        // Given
        val fileSystem = givenFileSystem()

        // When
        val result1 = fileSystem.getAllFoldersAndSubFolders()
        val result2 = fileSystem.getAllFoldersAndSubFolders()

        // Then
        assertEquals(result1, result2)
        assertEquals(result1, listOf(fileSystem.subFolders[0], fileSystem.subFolders[0].subFolders[0]))

    }
}