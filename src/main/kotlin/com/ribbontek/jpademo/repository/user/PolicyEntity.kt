package com.ribbontek.jpademo.repository.user

import com.ribbontek.jpademo.repository.abstracts.AbstractEntity
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(schema = "jpademo", name = "policy")
@AttributeOverride(name = "id", column = Column(name = "policy_id"))
data class PolicyEntity(
    @Column(nullable = false, length = 255)
    var permission: String,
    @ManyToMany(mappedBy = "policies", fetch = LAZY)
    val policies: Set<RoleEntity>? = null
) : AbstractEntity()
