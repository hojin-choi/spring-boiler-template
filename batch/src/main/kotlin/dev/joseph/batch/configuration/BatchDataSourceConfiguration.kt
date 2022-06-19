package dev.joseph.batch.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import javax.sql.DataSource

@Configuration
open class BatchDataSourceConfiguration(private val env: Environment) {

    @Bean
    @ConfigurationProperties("spring.datasource.batch")
    open fun batchHikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    open fun batchDataSource(
        @Qualifier("batchHikariConfig") batchHikariConfig: HikariConfig
    ): DataSource {
        return createDatasource(batchHikariConfig)
    }

    private fun createDatasource(hikariConfig: HikariConfig): HikariDataSource {
        return HikariDataSource(hikariConfig)
    }
}
