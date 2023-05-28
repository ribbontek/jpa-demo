package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.product.DiscountEntity
import com.ribbontek.jpademo.util.FakerUtil
import java.time.ZonedDateTime
import java.util.UUID

object DiscountEntityFactory {
    fun create(): DiscountEntity {
        return DiscountEntity(
            requestId = UUID.randomUUID(),
            discount = FakerUtil.price(),
            code = FakerUtil.alphanumeric(50),
            description = FakerUtil.alphanumeric(),
            expiresAt = ZonedDateTime.now().plusDays(3)
        )
    }
}
