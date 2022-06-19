package dev.joseph.api.controller

import dev.joseph.domain.service.SampleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(private val sampleService: SampleService) {

    @PostMapping("/sample")
    fun createSampleRecord(): String {
        sampleService.createSampleRecord("hello!!")
        return "ok"
    }
}
