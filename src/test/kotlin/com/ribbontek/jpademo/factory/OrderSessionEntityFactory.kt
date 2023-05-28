package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.order.OrderItemEntity
import com.ribbontek.jpademo.repository.order.OrderSessionEntity
import com.ribbontek.jpademo.repository.order.PaymentEntity
import com.ribbontek.jpademo.repository.user.UserEntity
import com.ribbontek.jpademo.util.FakerUtil

object OrderSessionEntityFactory {
    fun create(
        userEntity: UserEntity,
        paymentEntity: PaymentEntity? = null,
        orderItems: List<OrderItemEntity>? = null
    ): OrderSessionEntity {
        return OrderSessionEntity(
            user = userEntity,
            payment = paymentEntity,
            total = FakerUtil.price(),
            status = FakerUtil.alphanumeric(50),
            orderItems = orderItems?.toMutableSet()
        )
    }
}
