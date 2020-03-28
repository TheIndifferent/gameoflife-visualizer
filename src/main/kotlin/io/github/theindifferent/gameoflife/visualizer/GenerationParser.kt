package io.github.theindifferent.gameoflife.visualizer

class GenerationParser {

    private val generationRegex = Regex("(-?\\d+:-?\\d+,?)*")
    private val cellRegex = Regex("(-?\\d+):(-?\\d+)")

    fun parseGeneration(generation: String): List<Cell> {
        if (generation.isEmpty()) {
            return listOf()
        }
        if (!generationRegex.matches(generation)) {
            throw InvalidGenerationException()
        }
        return cellRegex.findAll(generation)
            .map { Cell(it.groupValues[1].toInt(), it.groupValues[2].toInt()) }
            .toList()
    }
}
