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
class HttpApi {

    private val log: Logger = LoggerFactory.getLogger(HttpApi::class.java)

    @PostMapping
    fun acceptNextGeneration(@RequestBody nextGeneration: String) : ResponseEntity<String> {
        log.info("Received generation: {}", nextGeneration)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }
}
