package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.user.PolicyEntity
import com.ribbontek.jpademo.util.FakerUtil

object PolicyEntityFactory {
    fun create(): PolicyEntity {
        return PolicyEntity(
            permission = FakerUtil.alphanumeric()
        )
    }
}
