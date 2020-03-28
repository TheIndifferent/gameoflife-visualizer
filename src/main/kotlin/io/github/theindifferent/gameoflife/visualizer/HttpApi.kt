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

@RestController("/")
class HttpApi(val model: GameOfLifeModel) {

    private val log: Logger = LoggerFactory.getLogger(HttpApi::class.java)

    @PostMapping
    fun acceptNextGeneration(@RequestBody nextGeneration: String): ResponseEntity<String> {
        log.info("Received generation: {}", nextGeneration)
        try {
            model.spawnNextGeneration(nextGeneration)
            return ResponseEntity(HttpStatus.ACCEPTED)
        } catch (invalidGen: InvalidGenerationException) {
            return ResponseEntity(
                    "Invalid generation",
                    HttpStatus.BAD_REQUEST)
        }
    }
}
