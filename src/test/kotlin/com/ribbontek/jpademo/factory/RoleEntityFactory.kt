package com.ribbontek.jpademo.factory

import com.ribbontek.jpademo.repository.user.PolicyEntity
import com.ribbontek.jpademo.repository.user.RoleEntity
import com.ribbontek.jpademo.repository.user.RoleType
import com.ribbontek.jpademo.repository.user.UserEntity
import com.ribbontek.jpademo.util.FakerUtil

object RoleEntityFactory {
    fun create(
        userEntity: UserEntity,
        roleType: RoleType? = null,
        policies: List<PolicyEntity> = emptyList()
    ): RoleEntity {
        return RoleEntity(
            displayName = FakerUtil.email(),
            roleType = roleType ?: FakerUtil.enum(),
            user = userEntity,
            policies = policies.toMutableSet()
        )
    }
}
