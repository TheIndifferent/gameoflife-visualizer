package io.github.theindifferent.gameoflife.visualizer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.swing.SwingUtilities
import kotlin.system.measureTimeMillis

@Component
class GenerationTimer(private val model: GameOfLifeModel,
                      private val queue: GenerationQueue) {

    private val log: Logger = LoggerFactory.getLogger(GenerationTimer::class.java)
    private var generationsPerSecond: Int = 25

    fun startTimer() {
        val timerThread = Thread(this::runTimer, "Generation Timer")
        timerThread.start()
    }

    private fun runTimer() {
        while (true) {
            val parsingTime = measureTimeMillis {
                val nextGeneration = queue.takeGeneration()
                SwingUtilities.invokeAndWait { model.updateGeneration(nextGeneration) }
            }
            log.info("Got generation and updated UI in {}ms", parsingTime)
            val sleepTime = (1000 / generationsPerSecond) - parsingTime
            if (sleepTime > 0) {
                Thread.sleep(sleepTime)
            }
        }
    }

    fun increaseGenerationsPerSecond() {
        if (generationsPerSecond == 1000) {
            log.info("Generations per second: {}", generationsPerSecond)
            return
        }
        if (generationsPerSecond == 1) {
            generationsPerSecond = 5
        } else {
            generationsPerSecond += 5
        }
        log.info("Generations per second: {}", generationsPerSecond)
    }

    fun decreaseGenerationsPerSecond() {
        if (generationsPerSecond == 1) {
            log.info("Generations per second: {}", generationsPerSecond)
            return
        }
        if (generationsPerSecond == 5) {
            generationsPerSecond = 1
        } else {
            generationsPerSecond -= 5
        }
        log.info("Generations per second: {}", generationsPerSecond)
    }
}
