package dev.joseph.batch

import dev.joseph.domain.DomainConfigurationLoader
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import kotlin.system.exitProcess

@SpringBootApplication
@EnableBatchProcessing
@Import(
    value = [
        DomainConfigurationLoader::class,
    ]
)
open class BatchApplication

fun main(args: Array<String>) {
    val exitCode = SpringApplication.exit(SpringApplication.run(BatchApplication::class.java, *args))
    exitProcess(exitCode)
}
