package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.product.CategoryEntity
import com.ribbontek.jpademo.repository.product.DiscountEntity
import com.ribbontek.jpademo.repository.product.ProductEntity
import com.ribbontek.jpademo.util.FakerUtil
import java.util.UUID

object ProductEntityFactory {
    fun create(
        discount: DiscountEntity? = null,
        category: CategoryEntity? = null
    ): ProductEntity {
        return ProductEntity(
            requestId = UUID.randomUUID(),
            discount = discount,
            category = category ?: CategoryEntity(categoryEnum = FakerUtil.enum()),
            quantity = FakerUtil.quantity().toLong(),
            price = FakerUtil.price(),
            sku = FakerUtil.alphanumeric(),
        )
    }
}
