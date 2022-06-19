package dev.joseph.domain.configuration

import dev.joseph.domain.entity.JpaEntityMarker
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.time.OffsetDateTime
import java.util.Optional.of
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackageClasses = [JpaEntityMarker::class],
    transactionManagerRef = "transactionManager",
    entityManagerFactoryRef = "entityManagerFactory"
)
open class JpaConfiguration(
    private val jpaProperties: JpaProperties
) {
    @Primary
    @Bean
    open fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val builder = EntityManagerFactoryBuilder(createJpaVendorAdapter(), jpaProperties.properties, null)
        return builder
            .dataSource(dataSource)
            .persistenceUnit("entityManager")
            .packages("dev.joseph.domain.entity")
            .build()
    }

    @Primary
    @Bean
    open fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    fun createJpaVendorAdapter(): JpaVendorAdapter {
        val adapter = HibernateJpaVendorAdapter()
        adapter.setShowSql(jpaProperties.isShowSql)
        adapter.setDatabase(jpaProperties.database)
        adapter.setDatabasePlatform(jpaProperties.databasePlatform)
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl)
        return adapter
    }

    @Bean
    open fun auditingDateTimeProvider() = DateTimeProvider { of(OffsetDateTime.now()) }
}
