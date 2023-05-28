package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.user.AddressTypeEntity
import com.ribbontek.jpademo.repository.user.UserAddressEntity
import com.ribbontek.jpademo.repository.user.UserEntity
import com.ribbontek.jpademo.util.FakerUtil

object UserAddressFactory {
    fun create(
        userEntity: UserEntity,
        addressTypeEntity: AddressTypeEntity
    ): UserAddressEntity {
        return UserAddressEntity(
            user = userEntity,
            addressType = addressTypeEntity,
            line = FakerUtil.addressLine(),
            suburb = FakerUtil.suburb(),
            state = FakerUtil.state(),
            postcode = FakerUtil.postcode(),
            country = FakerUtil.country()
        )
    }
}
