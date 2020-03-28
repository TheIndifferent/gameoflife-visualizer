package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component

@Component
class GameOfLifeModel {

    private val modelListeners: MutableList<GameOfLifeModelListener> = mutableListOf()
    private val generation: MutableList<Cell> = mutableListOf()

    fun spawnNextGeneration(nextGeneration: String) {
        generation.clear()
    }

    fun fireModelUpdate() {
        modelListeners.forEach(GameOfLifeModelListener::fireModelUpdate)
    }

    fun addModelListener(listener: GameOfLifeModelListener) {
        modelListeners.add(listener)
    }

    fun removeModelListener(listener: GameOfLifeModelListener) {
        modelListeners.remove(listener)
    }
}
