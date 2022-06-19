package dev.joseph.domain.service

import dev.joseph.domain.entity.sample.Sample
import dev.joseph.domain.entity.sample.SampleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class SampleService(private val sampleRepository: SampleRepository) {

    @Transactional
    open fun createSampleRecord(name: String): Sample {
        val sample = Sample(name = name)
        return sampleRepository.save(sample)
    }

    open fun getSampleRecord(id: Long): Sample {
        return sampleRepository.getById(id)
    }

    open fun getSampleRecords(): List<Sample> {
        return sampleRepository.findAll()
    }
}
