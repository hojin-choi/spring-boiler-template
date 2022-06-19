package dev.joseph.domain.entity.sample

import dev.joseph.domain.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, name = "name")
    val name: String
) : BaseEntity()
