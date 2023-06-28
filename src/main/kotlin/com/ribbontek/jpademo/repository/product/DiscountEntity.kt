package com.ribbontek.jpademo.repository.product

import com.ribbontek.jpademo.repository.abstracts.AbstractAdminEntityDelete
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

@Entity
@Table(name = "discount")
@AttributeOverride(name = "id", column = Column(name = "discount_id"))
@SQLDelete(sql = "update discount set deleted = true where discount_id = ? and version = ?")
data class DiscountEntity(
    @Column(nullable = false, unique = true, name = "discount_uuid")
    val requestId: UUID,
    @Column(nullable = false)
    var discount: BigDecimal,
    @Column(nullable = false, length = 50)
    var code: String? = null,
    @Column(length = 255)
    var description: String? = null,
    @Column(length = 255)
    var expiresAt: ZonedDateTime? = null
) : AbstractAdminEntityDelete()
