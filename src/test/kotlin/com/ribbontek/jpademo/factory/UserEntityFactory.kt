package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.user.UserEntity
import com.ribbontek.jpademo.util.FakerUtil
import java.util.UUID

object UserEntityFactory {
    fun create(): UserEntity {
        return UserEntity(
            email = FakerUtil.email(),
            firstName = FakerUtil.firstName(),
            lastName = FakerUtil.lastName(),
            idpUserName = UUID.randomUUID().toString(),
            idpStatus = FakerUtil.status()
        )
    }
}
