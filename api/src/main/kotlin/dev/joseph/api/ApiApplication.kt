package dev.joseph.api

import dev.joseph.domain.DomainConfigurationLoader
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    value = [
        DomainConfigurationLoader::class,
    ]
)
open class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
