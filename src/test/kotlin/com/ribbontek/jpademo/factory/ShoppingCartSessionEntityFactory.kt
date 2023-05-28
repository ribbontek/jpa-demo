package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.cart.ShoppingCartItemEntity
import com.ribbontek.jpademo.repository.cart.ShoppingCartSessionEntity
import com.ribbontek.jpademo.repository.user.UserEntity
import com.ribbontek.jpademo.util.FakerUtil
import java.util.UUID

object ShoppingCartSessionEntityFactory {
    fun create(
        userEntity: UserEntity,
        cartItems: List<ShoppingCartItemEntity>? = null
    ): ShoppingCartSessionEntity {
        return ShoppingCartSessionEntity(
            user = userEntity,
            requestId = UUID.randomUUID(),
            total = FakerUtil.price(),
            status = FakerUtil.alphanumeric(100),
            cartItems = cartItems?.toMutableSet()
        )
    }
}
