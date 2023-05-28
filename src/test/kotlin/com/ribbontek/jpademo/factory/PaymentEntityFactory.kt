package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.order.PaymentEntity
import com.ribbontek.jpademo.repository.user.UserEntity
import com.ribbontek.jpademo.util.FakerUtil

object PaymentEntityFactory {
    fun create(
        userEntity: UserEntity,
    ): PaymentEntity {
        return PaymentEntity(
            user = userEntity,
            amount = FakerUtil.price(),
            provider = FakerUtil.alphanumeric(100),
            status = FakerUtil.alphanumeric(100),
            reference = FakerUtil.alphanumeric()
        )
    }
}
