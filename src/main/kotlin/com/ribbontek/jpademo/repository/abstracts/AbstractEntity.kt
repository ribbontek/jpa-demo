package com.ribbontek.jpademo.repository.abstracts

import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.hibernate.annotations.Generated
import org.hibernate.generator.EventType
import java.io.Serializable
import java.time.ZonedDateTime

@MappedSuperclass
abstract class AbstractEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(event = [EventType.INSERT])
    @Column(insertable = false, updatable = false, nullable = false)
    val id: Long? = null

    @Generated(event = [EventType.INSERT])
    @Basic(optional = false)
    @Column(updatable = false, nullable = false, name = "created_at")
    var createdAt: ZonedDateTime? = null

    @Generated(event = [EventType.INSERT])
    @Column(insertable = false, name = "modified_at")
    var modifiedAt: ZonedDateTime? = null

    @Version
    @Column(nullable = false)
    var version: Int? = null
}
