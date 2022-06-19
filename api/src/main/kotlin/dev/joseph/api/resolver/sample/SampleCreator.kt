package dev.joseph.api.resolver.sample

import dev.joseph.api.generated.types.Sample
import org.springframework.stereotype.Component
import dev.joseph.domain.entity.sample.Sample as SampleEntity

@Component
open class SampleCreator {
    open fun create(sample: SampleEntity): Sample {
        return Sample(
            id = "${sample.id!!}",
            name = sample.name!!
        )
    }
}
