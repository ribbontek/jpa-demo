package com.ribbontek.jpademo.repository.order

import com.ribbontek.jpademo.repository.abstracts.AbstractEntity
import com.ribbontek.jpademo.repository.user.UserEntity
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "payment")
@AttributeOverride(name = "id", column = Column(name = "payment_id"))
data class PaymentEntity(
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    val user: UserEntity? = null,
    @Column(nullable = false)
    val amount: BigDecimal,
    @Column(nullable = false, length = 100)
    val provider: String,
    @Column(nullable = false, length = 100)
    val status: String,
    @Column(nullable = false, length = 255)
    val reference: String
) : AbstractEntity()
