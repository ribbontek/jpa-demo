package com.ribbontek.jpademo.repository.user

import com.ribbontek.jpademo.repository.abstracts.AbstractEntity
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType
import jakarta.persistence.AttributeOverride
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Type

enum class RoleType {
    BASIC, STANDARD, PREMIUM, ADMIN, SYSTEM
}

@Entity
@Table(schema = "jpademo", name = "role")
@AttributeOverride(name = "id", column = Column(name = "role_id"))
data class RoleEntity(
    @Column(nullable = false, length = 255)
    var displayName: String,
    @Column(nullable = false, columnDefinition = "role_type_enum")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType::class)
    var roleType: RoleType,
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    val user: UserEntity? = null,
    @ManyToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH])
    @JoinTable(
        schema = "jpademo",
        name = "role_to_policy",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "policy_id", referencedColumnName = "policy_id")]
    )
    var policies: MutableSet<PolicyEntity>? = null
) : AbstractEntity()
