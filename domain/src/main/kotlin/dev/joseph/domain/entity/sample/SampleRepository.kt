package dev.joseph.domain.entity.sample

import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long>
