package com.ribbontek.jpademo.repository.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    @Query("select user from UserEntity user left join fetch user.addresses where user.email = :email")
    fun findUserEntityByEmailWithEagerAddresses(email: String): UserEntity?

    @Query("select user from UserEntity user where has_role(:userId, :roleType) = true")
    fun findUserHavingRole(userId: Long, roleType: String): UserEntity?
}
