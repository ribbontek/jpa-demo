package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.order.OrderItemEntity
import com.ribbontek.jpademo.repository.order.OrderSessionEntity
import com.ribbontek.jpademo.repository.product.ProductEntity
import com.ribbontek.jpademo.util.FakerUtil

object OrderItemEntityFactory {
    fun create(
        session: OrderSessionEntity,
        product: ProductEntity
    ): OrderItemEntity {
        return OrderItemEntity(
            session = session,
            product = product,
            price = FakerUtil.price(),
            quantity = FakerUtil.quantity()
        )
    }
}
