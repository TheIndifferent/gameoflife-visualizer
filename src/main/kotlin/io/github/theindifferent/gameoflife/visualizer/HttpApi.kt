package io.github.theindifferent.gameoflife.visualizer

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class HttpApi {

    @PostMapping
    fun acceptNextGeneration(@RequestBody nextGeneration: String) : ResponseEntity<String> {
        println("Received next generation: " + nextGeneration)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }
}
