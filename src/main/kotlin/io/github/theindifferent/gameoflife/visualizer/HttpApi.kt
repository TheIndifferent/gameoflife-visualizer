package io.github.theindifferent.gameoflife.visualizer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class HttpApi(private val generationParser: GenerationParser,
              private val generationQueue: GenerationQueue) {

    private val log: Logger = LoggerFactory.getLogger(HttpApi::class.java)

    @PostMapping
    fun acceptNextGeneration(@RequestBody nextGenerationString: String): ResponseEntity<String> {
        log.info("Received generation: {}", nextGenerationString)
        try {

            val generation = generationParser.parseGeneration(nextGenerationString)
            generationQueue.putGeneration(generation)
            return ResponseEntity(HttpStatus.ACCEPTED)

        } catch (invalidGen: InvalidGenerationException) {
            return ResponseEntity(
                    "Invalid generation",
                    HttpStatus.BAD_REQUEST)
        }
    }
}
