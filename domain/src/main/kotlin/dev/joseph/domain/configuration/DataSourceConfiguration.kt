package dev.joseph.domain.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import javax.sql.DataSource

@Configuration
open class DataSourceConfiguration(private val env: Environment) {
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    open fun hikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Primary
    @Bean
    open fun dataSource(hikariConfig: HikariConfig): DataSource {
        return HikariDataSource(hikariConfig)
    }

    private fun isNotLocalAndTest(): Boolean {
        return !env.activeProfiles.contains("local") && !env.activeProfiles.contains("test")
    }

    private fun getPassword(): String {
        return "not implemented"
    }
}
