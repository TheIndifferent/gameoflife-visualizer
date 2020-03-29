package io.github.theindifferent.gameoflife.visualizer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.swing.SwingUtilities
import kotlin.system.measureTimeMillis

@RestController("/")
class HttpApi(val model: GameOfLifeModel) {

    private val GENERATIONS_PER_SECOND = 25
    private val TIME_PER_GENERATION = 1000 / GENERATIONS_PER_SECOND

    private val log: Logger = LoggerFactory.getLogger(HttpApi::class.java)
    private val parser: GenerationParser = GenerationParser()

    @PostMapping
    fun acceptNextGeneration(@RequestBody nextGenerationString: String): ResponseEntity<String> {
        log.info("Received generation: {}", nextGenerationString)
        try {

            val parsingTime = measureTimeMillis {
                val nextGeneration = parser.parseGeneration(nextGenerationString)
                SwingUtilities.invokeLater({ model.updateGeneration(nextGeneration) })
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
