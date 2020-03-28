package io.github.theindifferent.gameoflife.visualizer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

@RestController("/")
class HttpApi(val model: GameOfLifeModel) {

    private val GENERATIONS_PER_SECOND = 10
    private val TIME_PER_GENERATION = 1000 / GENERATIONS_PER_SECOND

    private val log: Logger = LoggerFactory.getLogger(HttpApi::class.java)

    @PostMapping
    fun acceptNextGeneration(@RequestBody nextGeneration: String): ResponseEntity<String> {
        log.info("Received generation: {}", nextGeneration)
        try {

            val parsingTime = measureTimeMillis {
                model.spawnNextGeneration(nextGeneration)
            }
            Thread.sleep(TIME_PER_GENERATION - parsingTime)

            return ResponseEntity(HttpStatus.ACCEPTED)

        } catch (invalidGen: InvalidGenerationException) {
            return ResponseEntity(
                    "Invalid generation",
                    HttpStatus.BAD_REQUEST)
        }
    }
}
