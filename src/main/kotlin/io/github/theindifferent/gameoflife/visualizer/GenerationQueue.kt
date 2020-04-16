package io.github.theindifferent.gameoflife.visualizer

import org.springframework.stereotype.Component
import java.util.concurrent.LinkedBlockingQueue

@Component
class GenerationQueue {
    private val queue = LinkedBlockingQueue<List<Cell>>(Short.MAX_VALUE.toInt())

    fun putGeneration(generation: List<Cell>) {
        queue.put(generation)
    }

    fun takeGeneration() : List<Cell> {
        return queue.take()
    }
}
