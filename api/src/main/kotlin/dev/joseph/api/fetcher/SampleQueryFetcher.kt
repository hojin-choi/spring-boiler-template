package dev.joseph.api.fetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import dev.joseph.api.generated.DgsConstants
import dev.joseph.api.generated.types.Sample
import dev.joseph.api.resolver.sample.SampleCreator
import dev.joseph.domain.service.SampleService

@DgsComponent
class SampleQueryFetcher(private val sampleService: SampleService, private val creator: SampleCreator) {

    @DgsQuery(field = DgsConstants.QUERY.Sample)
    fun getSample(@InputArgument id: String, environment: DgsDataFetchingEnvironment): Sample {
        val sample = sampleService.getSampleRecord(id.toLong())
        return creator.create(sample)
    }
}
