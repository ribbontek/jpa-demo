package com.ribbontek.jpademo.security

object SecurityTestFactory {

    fun principal(
        email: String,
        userId: Long,
        idpUserName: String
    ): Principal {
        return Principal(
            username = email,
            userId = userId,
            idpUserName = idpUserName
        )
    }
}
