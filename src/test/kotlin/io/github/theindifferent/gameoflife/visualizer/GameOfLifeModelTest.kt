package io.github.theindifferent.gameoflife.visualizer

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

internal class GameOfLifeModelTest {

    private val model: GameOfLifeModel = GameOfLifeModel()

    @Test
    fun `empty input produces empty output`() {
        assertEquals(
            listOf(),
            model.parseGeneration(""))
    }

    @Test
    fun `single element input produces single cell`() {
        assertEquals(
            listOf(Cell(11, 12)),
            model.parseGeneration("11:12"))
    }

    @Test
    fun `single negative element input produces single cell`() {
        assertEquals(
            listOf(Cell(-11, -12)),
            model.parseGeneration("-11:-12"))
    }

    @Test
    fun `single random element produces correct cell`() {
        val x = Random.nextInt()
        val y = Random.nextInt()
        assertEquals(
            listOf(Cell(x, y)),
            model.parseGeneration("$x:$y"))
    }

    @Test
    fun `two elements parsed correctly`() {
        val cell1 = Cell(Random.nextInt(), Random.nextInt())
        val cell2 = Cell(Random.nextInt(), Random.nextInt())
        assertEquals(
            listOf(cell1, cell2),
            model.parseGeneration("${cell1.x}:${cell1.y},${cell2.x}:${cell2.y}"))
    }
}
