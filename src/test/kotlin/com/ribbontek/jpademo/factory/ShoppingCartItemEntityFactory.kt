package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.cart.ShoppingCartItemEntity
import com.ribbontek.jpademo.repository.cart.ShoppingCartSessionEntity
import com.ribbontek.jpademo.repository.product.ProductEntity
import com.ribbontek.jpademo.util.FakerUtil

object ShoppingCartItemEntityFactory {
    fun create(
        session: ShoppingCartSessionEntity,
        productEntity: ProductEntity
    ): ShoppingCartItemEntity {
        return ShoppingCartItemEntity(
            session = session,
            product = productEntity,
            quantity = FakerUtil.quantity()
        )
    }
}
