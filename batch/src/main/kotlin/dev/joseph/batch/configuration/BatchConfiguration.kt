package dev.joseph.batch.configuration

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
open class BatchConfiguration(
    private val catalogTransactionManager: PlatformTransactionManager,
    @Qualifier("batchDataSource") dataSource: DataSource,
) : DefaultBatchConfigurer(dataSource) {

    override fun getTransactionManager(): PlatformTransactionManager {
        return catalogTransactionManager
    }
}
