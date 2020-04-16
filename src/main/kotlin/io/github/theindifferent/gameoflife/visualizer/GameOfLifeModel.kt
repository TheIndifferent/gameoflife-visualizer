package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component

@Component
class GameOfLifeModel {

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

    fun updateGeneration(nextGen: List<Cell>) {
        generation.clear()
        generation.addAll(nextGen)
        fireModelUpdate()
    }

    fun cells() : List<Cell> {
        return generation
    }
}
