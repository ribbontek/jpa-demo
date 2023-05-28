package com.ribbontek.jpademo.repository.abstracts

import com.ribbontek.jpademo.security.Principal
import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Version
import org.hibernate.annotations.Generated
import org.hibernate.generator.EventType
import org.springframework.security.core.context.SecurityContextHolder
import java.io.Serializable
import java.time.ZonedDateTime

@MappedSuperclass
abstract class AbstractAdminEntityDelete : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(event = [EventType.INSERT])
    @Column(insertable = false, updatable = false, nullable = false)
    val id: Long? = null

    @Generated(event = [EventType.INSERT])
    @Basic(optional = false)
    @Column(updatable = false, nullable = false, name = "created_at")
    var createdAt: ZonedDateTime? = null

    @Column(insertable = false, name = "created_by")
    var createdBy: String? = null

    @Generated(event = [EventType.INSERT])
    @Column(insertable = false, name = "modified_at")
    var modifiedAt: ZonedDateTime? = null

    @Column(insertable = false, name = "modified_by")
    var modifiedBy: String? = null

    @Generated(event = [EventType.INSERT])
    @Column(insertable = false, nullable = false)
    var deleted = false

    @Version
    @Column(nullable = false)
    var version: Int? = null

    @PrePersist
    fun prePersist() {
        SecurityContextHolder.getContext().authentication?.takeIf { it.principal is Principal }?.let {
            this.createdBy = (it.principal as Principal).username
        }
    }

    @PreUpdate
    fun preUpdate() {
        SecurityContextHolder.getContext().authentication?.takeIf { it.principal is Principal }?.let {
            this.modifiedBy = (it.principal as Principal).username
        }
    }
}
