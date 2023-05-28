package com.ribbontek.jpademo.repository.cart

import com.ribbontek.jpademo.repository.abstracts.AbstractEntity
import com.ribbontek.jpademo.repository.user.UserEntity
import jakarta.persistence.AttributeOverride
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "shopping_cart_session")
@AttributeOverride(name = "id", column = Column(name = "shopping_cart_session_id"))
data class ShoppingCartSessionEntity(
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    val user: UserEntity? = null,
    @Column(nullable = false, unique = true, name = "shopping_cart_session_uuid")
    val requestId: UUID,
    @Column(nullable = false)
    var total: BigDecimal,
    @Column(nullable = false, length = 100)
    var status: String,
    @OneToMany(
        cascade = [CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH],
        mappedBy = "session",
        fetch = FetchType.LAZY
    )
    var cartItems: MutableSet<ShoppingCartItemEntity>? = null
) : AbstractEntity()
