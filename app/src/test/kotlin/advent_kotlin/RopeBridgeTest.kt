package advent_kotlin

import advent_kotlin.domain.RopeBridge
import org.junit.Test
import kotlin.test.assertEquals

class RopeBridgeTest {
    @Test
    fun moveUp(){
        // Given
        val ropeBridge  = RopeBridge(mutableListOf( Pair(0,0), Pair(0, 0)))

        // When
        ropeBridge.moveUp()

        // Then
        assertEquals(ropeBridge.positions[0], Pair(0, 1))
    }
    @Test
    fun moveDown(){
        // Given
        val ropeBridge  = RopeBridge(mutableListOf( Pair(0,0), Pair(0, 0)))

        // When
        ropeBridge.moveDown()

        // Then
        assertEquals(ropeBridge.positions[0], Pair(0, -1))
    }
    @Test
    fun moveLeft(){
        // Given
        val ropeBridge  = RopeBridge(mutableListOf( Pair(0,0), Pair(0, 0)))

        // When
        ropeBridge.moveLeft()

        // Then
        assertEquals(ropeBridge.positions[0], Pair(-1, 0))
    }
    @Test
    fun moveRight(){
        // Given
        val ropeBridge  = RopeBridge(mutableListOf( Pair(0,0), Pair(0, 0)))

        // When
        ropeBridge.moveRight()

        // Then
        assertEquals(ropeBridge.positions[0], Pair(1, 0))
    }
    @Test
    fun updateTailPosition(){
        // Given
        val ropeBridgeExpectedTails: List<Pair<RopeBridge, Pair<Int, Int>>>  = listOf(
            Pair( RopeBridge(mutableListOf( Pair(0,2), Pair(0, 0))), Pair(0, 1)),
            Pair( RopeBridge(mutableListOf( Pair(1,2), Pair(0, 0))), Pair(1, 1)),
            Pair( RopeBridge(mutableListOf( Pair(0, 0), Pair(-1, -2))), Pair(0, -1)),
            Pair( RopeBridge(mutableListOf( Pair(2, 0), Pair(0, 0))), Pair( 1, 0)),
            Pair( RopeBridge(mutableListOf( Pair(2, 1), Pair(0, 0))), Pair(1, 1)),
            Pair( RopeBridge(mutableListOf( Pair(0, 0), Pair(-2, -1))), Pair( -1, 0)),
            Pair( RopeBridge(mutableListOf( Pair(2, 2), Pair(0, 0))), Pair( 1, 1)),
            Pair( RopeBridge(mutableListOf( Pair(0, 0), Pair(-2, 2))), Pair( -1, 1)),
        )

        ropeBridgeExpectedTails.forEach{ test ->
            val ropeBridge = test.first
            val expectedTail = test.second
            println(expectedTail)
            // When
            val result = ropeBridge.updateFollowingPosition(ropeBridge.positions[1], ropeBridge.positions[0])

            // Then
            assertEquals(expectedTail, result)
        }
    }
}