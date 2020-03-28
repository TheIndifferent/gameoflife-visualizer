package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component

@Component
class GameOfLifeModel {

    private val parser = GenerationParser()
    private val modelListeners: MutableList<GameOfLifeModelListener> = mutableListOf()
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
        val nextGen = parser.parseGeneration(nextGeneration)
        generation.clear()
        generation.addAll(nextGen)
        fireModelUpdate()
    }

    fun cells() : List<Cell> {
        return generation
    }
}
