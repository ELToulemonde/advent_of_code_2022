package advent_kotlin

import advent_kotlin.domain.Forest
import advent_kotlin.domain.nViewAbleTree
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ForestTest {
    @Test
    fun isTreeVisibleFromLeft() {
        // Given
        val forest = Forest(
            listOf(
                listOf(2, 1, 1),
                listOf(2, 1, 1),
                listOf(2, 1, 1)
            )
        )

        // When + Then
        assertTrue(forest.isVisibleFromLeft(0, 0))
        assertFalse(forest.isVisibleFromLeft(1, 0))
        assertFalse(forest.isVisibleFromLeft(2, 0))
    }

    @Test
    fun isTreeVisibleFromRight() {
        // Given
        val forest = Forest(
            listOf(
                listOf(2, 1, 1),
                listOf(2, 1, 1),
                listOf(2, 1, 1)
            )
        )

        // When + Then
        assertTrue(forest.isVisibleFromRight(0, 0))
        assertFalse(forest.isVisibleFromRight(1, 0))
        assertTrue(forest.isVisibleFromRight(2, 0))
    }

    @Test
    fun isTreeVisibleFromTop() {
        // Given
        val forest = Forest(
            listOf(
                listOf(2, 1, 1),
                listOf(1, 1, 1),
                listOf(3, 1, 1)
            )
        )

        // When + Then
        assertTrue(forest.isVisibleFromTop(0, 0))
        assertFalse(forest.isVisibleFromTop(0, 1))
        assertTrue(forest.isVisibleFromTop(0, 2))
    }

    @Test
    fun isTreeVisibleFromBottom() {
        // Given
        val forest = Forest(
            listOf(
                listOf(3, 1, 1),
                listOf(1, 1, 1),
                listOf(2, 1, 1)
            )
        )

        // When + Then
        assertTrue(forest.isVisibleFromBottom(0, 0))
        assertFalse(forest.isVisibleFromBottom(0, 1))
        assertTrue(forest.isVisibleFromBottom(0, 2))
    }

    @Test
    fun isTreeVisibleForest() {
        // Given
        val forest = Forest(
            listOf(
                listOf(3, 0, 3, 7, 3),
                listOf(2, 5, 5, 1, 2),
                listOf(6, 5, 3, 3, 2),
                listOf(3, 3, 5, 4, 9),
                listOf(3, 5, 3, 9, 0)
            )
        )

        // When + Then
        assertTrue(forest.isVisibleFromLeft(1, 1))
        assertFalse(forest.isVisibleFromRight(1, 1))
        assertFalse(forest.isVisibleFromBottom(1, 1))
        assertTrue(forest.isVisibleFromTop(1, 1))

        assertFalse(forest.isVisibleFromLeft(2, 1))
        assertTrue(forest.isVisibleFromRight(2, 1))
        assertFalse(forest.isVisibleFromBottom(2, 1))
        assertTrue(forest.isVisibleFromTop(2, 1))

        assertFalse(forest.isVisibleFromLeft(3, 1))
        assertFalse(forest.isVisibleFromRight(3, 1))
        assertFalse(forest.isVisibleFromBottom(3, 1))
        assertFalse(forest.isVisibleFromTop(3, 1))

        assertFalse(forest.isVisibleFromLeft(1, 2))
        assertTrue(forest.isVisibleFromRight(1, 2))
        assertFalse(forest.isVisibleFromBottom(1, 2))
        assertFalse(forest.isVisibleFromTop(1, 2))

        assertFalse(forest.isVisibleFromLeft(2, 2))
        assertFalse(forest.isVisibleFromRight(2, 2))
        assertFalse(forest.isVisibleFromBottom(2, 2))
        assertFalse(forest.isVisibleFromTop(2, 2))

        assertFalse(forest.isVisibleFromLeft(3, 2))
        assertTrue(forest.isVisibleFromRight(3, 2))
        assertFalse(forest.isVisibleFromBottom(3, 2))
        assertFalse(forest.isVisibleFromTop(3, 2))

        assertTrue(forest.isVisibleFromLeft(2, 3))
        assertFalse(forest.isVisibleFromRight(2, 3))
        assertTrue(forest.isVisibleFromBottom(2, 3))
        assertFalse(forest.isVisibleFromTop(2, 3))

        assertFalse(forest.isVisibleFromLeft(1, 3))
        assertFalse(forest.isVisibleFromRight(1, 3))
        assertFalse(forest.isVisibleFromBottom(1, 3))
        assertFalse(forest.isVisibleFromTop(1, 3))

        assertFalse(forest.isVisibleFromLeft(3, 3))
        assertFalse(forest.isVisibleFromRight(3, 3))
        assertFalse(forest.isVisibleFromBottom(3, 3))
        assertFalse(forest.isVisibleFromTop(3, 3))

        assertFalse(forest.isVisibleFromLeft(4, 1))
        assertTrue(forest.isVisibleFromRight(4, 1))
        assertFalse(forest.isVisibleFromBottom(4, 1))
        assertFalse(forest.isVisibleFromTop(4, 1))

        assertTrue(forest.isVisibleFromBottom(4, 4))
        assertTrue(forest.isVisibleFromTop(0, 0))
    }

    @Test
    fun numberOfVisibleTrees() {
        // Given
        val forest = Forest(
            listOf(
                listOf(3, 0, 3, 7, 3),
                listOf(2, 5, 5, 1, 2),
                listOf(6, 5, 3, 3, 2),
                listOf(3, 3, 5, 4, 9),
                listOf(3, 5, 3, 9, 0)
            )
        )

        // When + Then
        assertEquals(forest.numberOfVisibleTrees(), 21)
    }

    @Test
    fun testNViewAbleTree() {
        assertEquals(nViewAbleTree(listOf(3), 5), 1)
        assertEquals(nViewAbleTree(listOf(3, 5), 5), 2)
        assertEquals(nViewAbleTree(listOf<Int>(), 5), 0)
    }

    @Test
    fun scenicScore() {
        // Given
        val forest = Forest(
            listOf(
                listOf(3, 0, 3, 7, 3),
                listOf(2, 5, 5, 1, 2),
                listOf(6, 5, 3, 3, 2),
                listOf(3, 3, 5, 4, 9),
                listOf(3, 5, 3, 9, 0)
            )
        )

        // When + Then
        assertEquals(forest.scenicScore(2, 1), 4)
        assertEquals(forest.scenicScore(2, 3), 8)
    }
}