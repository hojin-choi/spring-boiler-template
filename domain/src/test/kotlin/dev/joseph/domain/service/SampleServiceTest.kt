package dev.joseph.domain.service

import com.ninjasquad.springmockk.MockkBean
import dev.joseph.domain.TestApplication
import dev.joseph.domain.entity.sample.Sample
import dev.joseph.domain.entity.sample.SampleRepository
import io.mockk.every
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.OffsetDateTime

@SpringBootTest(classes = [TestApplication::class])
@ActiveProfiles("local")
class SampleServiceTest {
    @MockkBean
    lateinit var sampleRepository: SampleRepository

    @Autowired
    lateinit var sampleService: SampleService

    fun getSample(id: Long?, name: String): Sample {
        val offsetDateTime = OffsetDateTime.now()
        val generatedId = id ?: 1L
        val dummySample = Sample(id = generatedId, name = name)
        dummySample.createdAt = offsetDateTime
        dummySample.updatedAt = offsetDateTime
        return dummySample
    }

    fun getSampleList(): List<Sample> = (1..10).map { index -> getSample(index.toLong(), "sampleName$index") }

    @Test
    fun `sample record를 생성한다`() {
        val givenName = "Hello Sample"

        val dummySample = getSample(null, givenName)
        every { sampleRepository.save(Sample(name = givenName)) } returns dummySample

        val result = sampleService.createSampleRecord(givenName)
        assertThat(result, `is`(dummySample))
    }

    @Test
    fun `sample record 단건을 조회한다`() {
        val givenId = 2L
        val dummySample = getSample(givenId, "sample")
        every { sampleRepository.getById(givenId) } returns dummySample

        val result = sampleService.getSampleRecord(givenId)
        assertThat(result, `is`(dummySample))
    }

    @Test
    fun `sample record 목록을 조회한다`() {
        val dummySampleList = getSampleList()
        every { sampleRepository.findAll() } returns dummySampleList

        val result = sampleService.getSampleRecords()
        assertThat(result, `is`(dummySampleList))
    }
}
