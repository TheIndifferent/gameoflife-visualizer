package io.github.theindifferent.gameoflife.visualizer

import org.junit.jupiter.api.assertThrows
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals


internal class GenerationParserTest {

    private val parser: GenerationParser = GenerationParser()

    @Test
    fun `empty input produces empty output`() {
        assertEquals(
            listOf(),
            parser.parseGeneration(""))
    }

    @Test
    fun `single element input produces single cell`() {
        assertEquals(
            listOf(Cell(11, 12)),
            parser.parseGeneration("11:12"))
    }

    @Test
    fun `single negative element input produces single cell`() {
        assertEquals(
            listOf(Cell(-11, -12)),
            parser.parseGeneration("-11:-12"))
    }

    @Test
    fun `single random element produces correct cell`() {
        val x = Random.nextInt()
        val y = Random.nextInt()
        assertEquals(
            listOf(Cell(x, y)),
            parser.parseGeneration("$x:$y"))
    }

    @Test
    fun `two elements parsed correctly`() {
        val cell1 = Cell(Random.nextInt(), Random.nextInt())
        val cell2 = Cell(Random.nextInt(), Random.nextInt())
        assertEquals(
            listOf(cell1, cell2),
            parser.parseGeneration("${cell1.x}:${cell1.y},${cell2.x}:${cell2.y},"))
    }

    @Test
    fun `invalid coordinates separator results in exception`() {
        assertThrows<InvalidGenerationException> {
            parser.parseGeneration("11;12")
        }
    }

    @Test
    fun `invalid cell separator results in exception`() {
        assertThrows<InvalidGenerationException> {
            parser.parseGeneration("11:12.13:14")
        }
    }
}
