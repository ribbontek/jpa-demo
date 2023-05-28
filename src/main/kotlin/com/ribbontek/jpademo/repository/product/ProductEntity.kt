package com.ribbontek.jpademo.repository.product

import com.ribbontek.jpademo.repository.abstracts.AbstractAdminEntityDelete
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "product")
@AttributeOverride(name = "id", column = Column(name = "product_id"))
@SQLDelete(sql = "update product set deleted = true where product_id = ?")
data class ProductEntity(
    @Column(nullable = false, unique = true, name = "product_uuid")
    val requestId: UUID,
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    var discount: DiscountEntity? = null,
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    @ManyToOne(optional = false)
    var category: CategoryEntity,
    @Column(nullable = false)
    var quantity: Long,
    @Column(nullable = false)
    var price: BigDecimal,
    @Column(nullable = false, length = 255)
    var sku: String? = null
) : AbstractAdminEntityDelete()
