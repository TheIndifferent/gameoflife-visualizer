package io.github.theindifferent.gameoflife.visualizer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.swing.SwingUtilities
import kotlin.system.measureTimeMillis

@Component
class GenerationTimer(private val model: GameOfLifeModel, private val parser: GenerationParser) {

    private val log: Logger = LoggerFactory.getLogger(GenerationTimer::class.java)
    private var generationsPerSecond: Int = 25

    fun parseThenUpdateThenWait(nextGenerationString: String) {
        val parsingTime = measureTimeMillis {
            val nextGeneration = parser.parseGeneration(nextGenerationString)
            SwingUtilities.invokeAndWait { model.updateGeneration(nextGeneration) }
        }
        val sleepTime = (1000 / generationsPerSecond) - parsingTime
        if (sleepTime > 0) {
            Thread.sleep(sleepTime)
        }
    }

    fun increaseGenerationsPerSecond() {
        if (generationsPerSecond == 1000) {
            log.info("Generation speed: {}", generationsPerSecond)
            return
        }
        if (generationsPerSecond == 1) {
            generationsPerSecond = 5
        } else {
            generationsPerSecond += 5
        }
        log.info("Generation speed: {}", generationsPerSecond)
    }

    fun decreaseGenerationsPerSecond() {
        if (generationsPerSecond == 1) {
            log.info("Generation speed: {}", generationsPerSecond)
            return
        }
        if (generationsPerSecond == 5) {
            generationsPerSecond = 1
        } else {
            generationsPerSecond -= 5
        }
        log.info("Generation speed: {}", generationsPerSecond)
    }
}
