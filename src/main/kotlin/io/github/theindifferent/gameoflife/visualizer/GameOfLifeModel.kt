package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component

@Component
class GameOfLifeModel {

    private val modelListeners: MutableList<GameOfLifeModelListener> = mutableListOf()
    private val generationRegex: Regex = Regex("((-?\\d+):(-?\\d+),?)*")
    private val generation: MutableList<Cell> = mutableListOf()

    fun addModelListener(listener: GameOfLifeModelListener) {
        modelListeners.add(listener)
    }

    fun removeModelListener(listener: GameOfLifeModelListener) {
        modelListeners.remove(listener)
    }

    fun fireModelUpdate() {
        modelListeners.forEach(GameOfLifeModelListener::fireModelUpdate)
    }

    fun spawnNextGeneration(nextGeneration: String) {
        generation.clear()
        fireModelUpdate()
    }

    fun parseGeneration(generation: String) : List<Cell> {
        if (generation.isEmpty()) {
            return listOf()
        }
        val result = mutableListOf<Cell>()
        val match = generationRegex.matchEntire(generation) ?: throw InvalidGenerationException()
        val groups = match.groups.drop(1)
        for (group in 0..groups.lastIndex step 3) {
            val x = groups[group + 1]?.value?.toInt() ?: throw InvalidGenerationException()
            val y = groups[group + 2]?.value?.toInt() ?: throw InvalidGenerationException()
            val cell = Cell(x, y)
            result.add(cell)
        }
        return result
    }
}
